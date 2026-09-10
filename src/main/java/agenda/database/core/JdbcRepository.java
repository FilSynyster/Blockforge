package agenda.database.core;

import java.util.List;

public interface JdbcRepository<T, ID> {

    public List<T> findAll();

    public T findById(ID id);

    public T create(T entity);

    public T update(T entity);

    public boolean deleteById(ID id);

}
