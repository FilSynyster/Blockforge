package agenda.database.ormlite;

import agenda.database.ormlite.configuration.ContextConfig;
import agenda.database.ormlite.configuration.ConfigFileProperties;
import agenda.database.core.*;

import java.util.List;

public class OrmlitePersistenceContext implements PersistenceContext {

    private ClassScanner scanner;
    private RepositoryRegistry repositoryRegistry = new RepositoryRegistry();
    private OrmLiteConfigHandler ormLiteConfigHandler;

    public OrmlitePersistenceContext(ContextConfig config) {
        scanner = new ClassScanner();
        scanner.setEntityPackage(config.get(ConfigFileProperties.ENTITY_PACKAGE_PROPERTY));
        scanner.setRootPackage(config.get(ConfigFileProperties.ROOT_PACKAGE_PROPERTY));
        ormLiteConfigHandler = new OrmLiteConfigHandler(scanner, config.get(ConfigFileProperties.URL_PROPERTY));
        findCreateAndRegisterRepositories();
    }


    @Override
    public void findCreateAndRegisterRepositories() {
        List<Class<?>> interfaces = scanner.findSubInterfaces(DataRepository.class);
        for (Class<?> repInterface : interfaces ) {
            Class<?> entityClass = RepositoryHandler.getParamClass(repInterface);
            DataRepository ormliteRep = ormLiteConfigHandler.createOrmliteRepository(entityClass);
            DataRepository<?,?> repository = RepositoryHandler.createRepository(repInterface, ormliteRep);
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
