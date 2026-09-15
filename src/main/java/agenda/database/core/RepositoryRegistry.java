package agenda.database.core;

import java.util.HashMap;
import java.util.Map;

public class RepositoryRegistry {

    private Map<Class<?>, DataRepository> repositories = new HashMap<>();

    public void registerRepository(Class<?> entity, DataRepository repository) {
        repositories.put(entity, repository);
    }

    public <R extends DataRepository<?,?>> R getRepository(Class<?> entity) {
        return (R) repositories.get(entity);
    }

}
