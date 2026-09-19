package agenda.database.core;

import java.util.HashMap;
import java.util.Map;

public class PersistenceContextRegistry {

    private Map<String, PersistenceContext> persistenceContexts = new HashMap<>();

    public void register(String name, PersistenceContext persistenceContext) {
        persistenceContexts.put(name, persistenceContext);
    }

    public <T extends PersistenceContext> T get(String name, Class<T> type) {
        return (T) persistenceContexts.get(name);
    }

    public boolean containsName(String name) {
        return persistenceContexts.containsKey(name);
    }
}
