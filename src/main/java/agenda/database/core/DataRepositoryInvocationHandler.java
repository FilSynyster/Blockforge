package agenda.database.core;

import agenda.database.annotations.Query;
import agenda.database.exceptions.RepositoryQueryNotFoundException;
import agenda.database.sql.SQLDocument;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DataRepositoryInvocationHandler implements InvocationHandler {

    //implementação real
    private final DataRepository<?,?> dataRepository;
    private final Class<?> dataRepositoryClass;
    private final SQLDocument sqlDocument;

    public DataRepositoryInvocationHandler(DataRepository<?,?> dataRepository, SQLDocument sqlDocument) {
        this.dataRepository = dataRepository;
        this.dataRepositoryClass = dataRepository.getClass();
        this.sqlDocument = sqlDocument;
    }

    public boolean containsMethod(Class<?> clazz, String name) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String[] toStringParamArray(Object[] args) {
        if (args == null || args.length == 0 || args[0] == null) {
            return null;
        }

        String[] parameters = new String[args.length];

        for (int i = 0; i < args.length; i++) {
            parameters[i] = String.valueOf(args[i]);
        }

        return parameters;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (!containsMethod(dataRepositoryClass, method.getName())) {

            String sql = null;
            String[] params = null;

            if (method.isAnnotationPresent(Query.class)) {

                sql = method.getAnnotation(Query.class).value();

            } else if (sqlDocument.containsScript(method.getName())) {

                sql = sqlDocument.getScript(method.getName());

            } else {
                throw new RepositoryQueryNotFoundException(
                        "BlockForge says: No script found for method: " + method.getName()
                );
            }

            return dataRepository.nativeQuery(sql, method.getGenericReturnType(), toStringParamArray(args));
        }

        Method dataRepositoryMethod = dataRepositoryClass.getMethod(method.getName(), method.getParameterTypes());
        return dataRepositoryMethod.invoke(dataRepository, args);
    }
}