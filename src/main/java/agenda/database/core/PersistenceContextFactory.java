package agenda.database.core;

import agenda.database.eclipselink.JpaPersistenceContext;
import agenda.database.ormlite.OrmlitePersistenceContext;
import agenda.database.ormlite.configuration.ConfigDocument;
import agenda.database.ormlite.configuration.Configuration;
import agenda.database.ormlite.configuration.Context;

public class PersistenceContextFactory {

    private PersistenceContextRegistry persistenceContextRegistry = new  PersistenceContextRegistry();
    private ConfigDocument configs = new Configuration().load("META-INF/ormlite-config.xml");

    public PersistenceContext createOrmlitePersistenceContext(String contextName) {
        PersistenceContext context = new OrmlitePersistenceContext(configs.getContextByName(contextName));
        context.initialize();
        persistenceContextRegistry.register(contextName, context);
        return context;
    }

    //A DEFINIR! AINDA NAO FUNCIONA
    public PersistenceContext createJpaPersistenceContext() {
        PersistenceContext context = new JpaPersistenceContext();
        context.initialize();
        persistenceContextRegistry.register(null, context);
        return context;
    }

    public void createAllOrmlitePersistenceContexts() {
        for (Context context : configs.getContexts()) {
            String contextName = context.getName();
            if (!persistenceContextRegistry.containsName(contextName)) {
                PersistenceContext persistenceContext = createOrmlitePersistenceContext(contextName);
                persistenceContextRegistry.register(contextName, persistenceContext);
            }
        }
    }

    public PersistenceContextRegistry getPersistenceContextRegistry() {
        return persistenceContextRegistry;
    }
}
