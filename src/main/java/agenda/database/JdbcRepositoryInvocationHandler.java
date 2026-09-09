package agenda.database;

import agenda.database.annotation.NamedQuery;
import agenda.database.annotation.Query;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdbcRepositoryInvocationHandler<T, ID> implements InvocationHandler {

    //implementação real
    private JdbcRepository<T,ID> jdbcRepository;

    public JdbcRepositoryInvocationHandler(JdbcRepository<T,ID> jdbcRepository) {
        this.jdbcRepository = jdbcRepository;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        boolean simpleQuery = method.isAnnotationPresent(Query.class);
        boolean namedQuery = method.isAnnotationPresent(NamedQuery.class);

        if (simpleQuery) {
            System.out.println("Valor da anotação: "+method.getAnnotation(Query.class).value());
            //IMPLEMENTAR LÓGICA
        }

        return switch (method.getName()) {
            case "findById" -> jdbcRepository.findById((ID) args[0]);
            case "create" -> jdbcRepository.create((T) args[0]);
            case "update" -> jdbcRepository.update((T) args[0]);
            case "deleteById" -> jdbcRepository.deleteById((ID) args[0]);
            case "findAll" -> jdbcRepository.findAll();
            default -> throw new UnsupportedOperationException(method.getName());
        };

    }
}
