package agenda.database.core;

import java.lang.reflect.Type;
import java.util.List;

public interface DataRepository<T, ID> {

    public List<T> findAll();

    public T findById(ID id);

    public T create(T entity);

    public T update(T entity);

    public boolean deleteById(ID id);

    /**
     * Executes a native query and returns its result according to the
     * specified return type.
     *
     * @param sql the native query to be executed
     * @param returnType the type expected for the query result
     * @param args the parameters used by the query
     * @param <R> the result type
     * @return the query result
     * @throws jakarta.persistence.PersistenceException if the query cannot be executed
     *         or the requested return type is not supported
     */
    public <R> R nativeQuery(String sql, Type returnType, String...args);


}
