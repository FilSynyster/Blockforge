package agenda.database;

import agenda.app.models.Contact;
import agenda.database.manager.DatabaseManager;

public class Test {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:database/base.db";

        DatabaseManager manager = new DatabaseManager("agenda", url);
        manager.createTablesIfNotExists();
        manager.findCreateAndRegisterRepositories();

        ContactRepository contactRepository = manager.getRepository(Contact.class);

        System.out.println(contactRepository.findById(1L));
    }

}
