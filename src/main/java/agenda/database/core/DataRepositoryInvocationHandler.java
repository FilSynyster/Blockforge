package agenda.database.core;

import agenda.database.annotations.Query;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DataRepositoryInvocationHandler implements InvocationHandler {

    //implementação real
    private DataRepository<?,?> dataRepository;
    private Class<?> dataRepositoryClass;

    public DataRepositoryInvocationHandler(DataRepository<?,?> dataRepositoryClass) {
        this.dataRepository = dataRepositoryClass;
        this.dataRepositoryClass = dataRepositoryClass.getClass();
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

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (!containsMethod(dataRepositoryClass, method.getName())) {
            if (method.isAnnotationPresent(Query.class)) {
                String sql = method.getAnnotation(Query.class).value();
                String[] parameters = null;
                if (args != null && args.length > 0 && args[0] != null) {
                    parameters = Arrays.stream(args)
                            .map(String::valueOf)
                            .toArray(String[]::new);
                }
                return dataRepository.nativeQuery(sql, method.getGenericReturnType(), parameters);
            } else {
                //SQL DO ARQUIVO XML
            }
        }

        Method dataRepositoryMethod = dataRepositoryClass.getMethod(method.getName(), method.getParameterTypes());
        return dataRepositoryMethod.invoke(dataRepository, args);
    }
}