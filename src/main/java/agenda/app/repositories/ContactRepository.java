package agenda.app.repositories;

import agenda.database.JdbcRepository;
import agenda.database.manager.SqliteRepositoryImpl;
import agenda.app.models.Contact;

public interface ContactRepository extends JdbcRepository<Contact, Long> {

}
