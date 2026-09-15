package agenda.database.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class RepositoryHandler {

    public static Class<?> getParamClass(Class<?>  repositoryInterface) {
        ParameterizedType type = (ParameterizedType) repositoryInterface.getGenericInterfaces()[0];
        Type[] argTypes = type.getActualTypeArguments();
        return (Class<?>) argTypes[0];
    }

    public static DataRepository<?,?> createRepository(
            Class<?> repositoryInterface,
            DataRepository<?,?> realRepository
    ) {
        //Cria a implementação dinâmica de DataRepository
        ClassLoader classLoader = repositoryInterface.getClassLoader();
        Class<?>[] interfaces = { repositoryInterface };

        return (DataRepository<?,?>) Proxy.newProxyInstance(
                classLoader,
                interfaces,
                new DataRepositoryInvocationHandler(realRepository)
        );
    }

}
