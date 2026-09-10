package agenda.app.repositories;

import agenda.database.core.JdbcRepository;
import agenda.app.models.Contact;

public interface ContactRepository extends JdbcRepository<Contact, Long> {

}
