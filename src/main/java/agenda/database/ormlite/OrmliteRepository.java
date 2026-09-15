package agenda.database.ormlite;

import agenda.database.core.DataRepository;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class OrmliteRepository<T, ID> implements DataRepository<T, ID> {

    private Dao<T, ID> dao;

    public OrmliteRepository(ConnectionSource connectionSource, Class<?> clazz) {
        try {
            dao = DaoManager.createDao(connectionSource, (Class<T>)clazz);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T findById(ID id) {
        try {
            return (T) dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T create(T entity) {
        try {
            dao.create(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public T update(T entity) {
        try {
            dao.update(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public boolean deleteById(ID id) {
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
