package agenda.app;

import agenda.database.PersistenceContext;
import agenda.database.core.PersistenceUnit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PersistenceHandler {

    private PersistenceHandler() {
    }

    private static final PersistenceUnit UNITY;

    static {
        PersistenceContext persist = new PersistenceContext("agenda");

        Properties properties = new Properties();

        try (InputStream input = PersistenceHandler.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UNITY = persist.createNewPersistenceUnit(properties);
        UNITY.createTablesIfNotExists();
        UNITY.findCreateAndRegisterRepositories();
    }

    public static PersistenceUnit getCurrentPersistenceUnity() {
        return UNITY;
    }

}
