package agenda.database.core;

import agenda.database.ormlite.OrmlitePersistenceContext;
import agenda.database.ormlite.configuration.PersistenceConfig;

public class PersistenceContextFactory {

    private PersistenceConfig config = new PersistenceConfig("persistence-config.xml");
    private PersistenceContextRegistry persistenceContextRegistry = new  PersistenceContextRegistry();

    public PersistenceContext createPersistenceContext(String contextName) {
        PersistenceContext context = new OrmlitePersistenceContext(config.getContextConfig(contextName));
        context.findCreateAndRegisterRepositories();
        persistenceContextRegistry.registerPersistenceContext(contextName, context);
        return context;
    }

    public void createAllPersistenceContexts() {
        for (String contextName : config.getContextConfigs().keySet()) {
            if (persistenceContextRegistry.getPersistenceContext(contextName) == null) {
                createPersistenceContext(contextName);
            }
        }
    }
}
