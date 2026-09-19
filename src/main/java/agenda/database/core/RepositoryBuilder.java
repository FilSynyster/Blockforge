package agenda.database.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class RepositoryBuilder {

    public static Class<?> getParamClass(Class<?>  repositoryInterface) {
        ParameterizedType type = (ParameterizedType) repositoryInterface.getGenericInterfaces()[0];
        Type[] argTypes = type.getActualTypeArguments();
        return (Class<?>) argTypes[0];
    }

    public static DataRepository<?,?> createRepository(
            Class<?> repositoryInterfaceClass,
            DataRepository<?,?> dataRepository
    ) {
        //Cria a implementação dinâmica de DataRepository
        ClassLoader classLoader = repositoryInterfaceClass.getClassLoader();
        Class<?>[] interfaces = { repositoryInterfaceClass };

        return (DataRepository<?,?>) Proxy.newProxyInstance(
                classLoader,
                interfaces,
                new DataRepositoryInvocationHandler(dataRepository)
        );
    }

}
