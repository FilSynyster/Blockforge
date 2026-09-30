package agenda.database.ormlite;

import agenda.database.core.*;
import agenda.database.ormlite.configuration.Context;
import agenda.database.sql.SQLDocument;
import agenda.database.sql.SQLFileManager;

import java.util.List;
import java.util.Map;

public class OrmlitePersistenceContext implements PersistenceContext {

    private final ClassScanner scanner;
    private final RepositoryRegistry repositoryRegistry = new RepositoryRegistry();
    private final OrmLiteConfigHandler ormLiteConfigHandler;

    public OrmlitePersistenceContext(Context config) {
        scanner = new ClassScanner(config);
        ormLiteConfigHandler = new OrmLiteConfigHandler(scanner, config.getContextConfig().getUrl());
        initialize();
    }


    @Override
    public void initialize() {
        SQLFileManager sqlFileManager = SQLFileManager.createDefault();
        Map<Class<?>, SQLDocument> documents = sqlFileManager.load();
        List<Class<?>> interfaces = scanner.findSubInterfaces(DataRepository.class);
        for (Class<?> repInterface : interfaces ) {
            Class<?> entityClass = RepositoryBuilder.getParamClass(repInterface);
            DataRepository<?,?> ormliteRep = ormLiteConfigHandler.createOrmliteRepository(entityClass);
            DataRepository<?,?> repository = RepositoryBuilder.createRepository(repInterface, ormliteRep, documents.get(repInterface));
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
