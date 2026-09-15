package agenda.database.core;

import java.util.HashMap;
import java.util.Map;

public class PersistenceContextRegistry {

    private Map<String, PersistenceContext> persistenceContexts = new HashMap<>();

    public void registerPersistenceContext(String name, PersistenceContext persistenceContext) {
        persistenceContexts.put(name, persistenceContext);
    }

    public PersistenceContext getPersistenceContext(String name) {
        return persistenceContexts.get(name);
    }

}
