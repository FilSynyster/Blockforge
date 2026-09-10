package agenda.database;

import agenda.database.core.PersistenceUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PersistenceContext {

    private Map<String, PersistenceUnit> persistenceUnityMap = new HashMap<>();

    private String packageName;
    public PersistenceContext(String packageName) {
        this.packageName = packageName;
    }

    public PersistenceUnit createNewPersistenceUnit(Properties props) {

        if (props == null) {
            throw new NullPointerException("Database properties is null");
        }

        if (!props.containsKey("url")) {
            throw new NullPointerException("Missing database url");
        }

        String url = null;
        if ((url = props.getProperty("url")) == null) {
            throw new NullPointerException("The database url is null");
        }

        String persistenceUnityName = null;
        if (persistenceUnityMap.get(persistenceUnityName = props.getProperty("unity-name")) != null) {
            throw new IllegalStateException(
                    "The persistence unity \""+persistenceUnityName+"\" is already defined"
            );
        }

        PersistenceUnit newPersistenceUnity = new PersistenceUnit(this.packageName, url);
        newPersistenceUnity.createTablesIfNotExists();
        newPersistenceUnity.findCreateAndRegisterRepositories();
        return newPersistenceUnity;
    }

    public PersistenceUnit getPersistenceUnity(String persistenceUnityName) {
        return persistenceUnityMap.get(persistenceUnityName);
    }

}
