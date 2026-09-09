package agenda.database;

import agenda.database.manager.DatabaseManager;

public final class DatabaseHandler {

    private static DatabaseManager databaseManager;

    private DatabaseHandler() {
    }

    public static DatabaseManager getDatabaseManager() {

        String packageName = "agenda";
        String url = "jdbc:sqlite:database/data.db";
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(packageName, url);
            databaseManager.createTablesIfNotExists();
            databaseManager.findCreateAndRegisterRepositories();
        }

        return databaseManager;
    }


    public static void closeConnection() {
        getDatabaseManager().closeConnection();
    }
}
