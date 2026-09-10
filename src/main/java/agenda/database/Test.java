package agenda.database;

import agenda.app.models.Contact;
import agenda.app.repositories.ContactRepository;
import agenda.database.core.PersistenceUnit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {

    public static void main(String[] args) {

        Properties prop = new Properties();

        try (InputStream in = Test.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PersistenceContext persist = new PersistenceContext("agenda");
        PersistenceUnit unit = persist.createNewPersistenceUnit(prop);
        unit.createTablesIfNotExists();
        unit.findCreateAndRegisterRepositories();

        ContactRepository contactRepository = unit.getRepository(Contact.class);
        contactRepository.findAll().forEach(System.out::println);
    }

}
