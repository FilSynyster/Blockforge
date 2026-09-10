package agenda.database.core;

import agenda.database.annotations.NamedQuery;
import agenda.database.annotations.Query;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdbcRepositoryInvocationHandler implements InvocationHandler {

    //implementação real
    private JdbcRepository<?,?> jdbcRepositoryImpl;

    public JdbcRepositoryInvocationHandler(JdbcRepository<?,?> jdbcRepositoryImpl) {
        this.jdbcRepositoryImpl = jdbcRepositoryImpl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        boolean simpleQuery = method.isAnnotationPresent(Query.class);
        boolean namedQuery = method.isAnnotationPresent(NamedQuery.class);

        if (simpleQuery) {
            System.out.println("Valor da anotação: "+method.getAnnotation(Query.class).value());
            //IMPLEMENTAR LÓGICA
        }

        //É aqui que a mágica acontece!
        Class<?> jdbcRepositoryClass = jdbcRepositoryImpl.getClass();
        Method jdbcRepositoryMethod = jdbcRepositoryClass.getMethod(method.getName(), method.getParameterTypes());
        Object result = jdbcRepositoryMethod.invoke(jdbcRepositoryImpl, args);

        return result;
    }
}
