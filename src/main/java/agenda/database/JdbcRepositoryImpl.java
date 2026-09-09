package agenda.database;

import agenda.app.models.Contact;

import java.util.List;

public class JdbcRepositoryImpl implements JdbcRepository<Contact, Long> {

    private List<Contact> connection;

    public JdbcRepositoryImpl(List<Contact> connection) {
        this.connection = connection;
    }

    @Override
    public List<Contact> findAll() {
        return connection;
    }

    @Override
    public Contact findById(Long aLong) {
        for (Contact c : connection) {
            if (c.getId() == aLong) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Contact create(Contact entity) {
        connection.add(entity);
        return entity;
    }

    @Override
    public Contact update(Contact entity) {
        connection.set(connection.indexOf(entity), entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        for (Contact c : connection) {
            if (c.getId() == id) {
                connection.remove(c);
            }
        }
        return true;
    }

    public Contact findByName(String name) {
        return connection.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }
}
