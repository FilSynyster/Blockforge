package agenda.database;

import agenda.app.models.Contact;
import agenda.database.annotation.Query;

public interface ContactRepository extends JdbcRepository<Contact, Long> {

    @Query("SELECT * FROM contact_tbl WHERE name = ?")
    Contact findByName(String name);

}
