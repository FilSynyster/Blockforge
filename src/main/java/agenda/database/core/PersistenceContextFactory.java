package agenda.database.core;

import agenda.database.eclipselink.JpaPersistenceContext;
import agenda.database.ormlite.OrmlitePersistenceContext;
import agenda.database.ormlite.configuration.ConfigDocument;
import agenda.database.ormlite.configuration.Configuration;
import agenda.database.ormlite.configuration.Context;

import java.util.Map;

public class PersistenceContextFactory {

    public static final int ORMLITE = 0;
    public static final int JPA = 1;

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

    public PersistenceContext createPersistenceContext(int providerType, String contextName) {
        if ( providerType == ORMLITE ) {
            return createOrmlitePersistenceContext(contextName);
        } else if ( providerType == JPA ) {
            return createJpaPersistenceContext();
        } else {
            throw new IllegalArgumentException("BlockForge says: Unknown provider type " + providerType);
        }
    }

    public PersistenceContextRegistry getPersistenceContextRegistry() {
        return persistenceContextRegistry;
    }
}
