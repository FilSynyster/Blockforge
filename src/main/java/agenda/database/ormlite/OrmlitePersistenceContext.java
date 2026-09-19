package agenda.database.ormlite;

import agenda.database.core.*;
import agenda.database.ormlite.configuration.Context;

import java.util.List;

public class OrmlitePersistenceContext implements PersistenceContext {

    private ClassScanner scanner;
    private RepositoryRegistry repositoryRegistry = new RepositoryRegistry();
    private OrmLiteConfigHandler ormLiteConfigHandler;

    public OrmlitePersistenceContext(Context config) {
        scanner = new ClassScanner(config);
        ormLiteConfigHandler = new OrmLiteConfigHandler(scanner, config.getContextConfig().getUrl());
        initialize();
    }


    @Override
    public void initialize() {
        List<Class<?>> interfaces = scanner.findSubInterfaces(DataRepository.class);
        for (Class<?> repInterface : interfaces ) {
            Class<?> entityClass = RepositoryBuilder.getParamClass(repInterface);
            DataRepository<?,?> ormliteRep = ormLiteConfigHandler.createOrmliteRepository(entityClass);
            DataRepository<?,?> repository = RepositoryBuilder.createRepository(repInterface, ormliteRep);
            repositoryRegistry.registerRepository(entityClass, repository);
        }
    }

    public RepositoryRegistry getRepositoryRegistry() {
        return repositoryRegistry;
    }

    public void closeConnection() {
        try {
            ormLiteConfigHandler.getConnectionSource().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
