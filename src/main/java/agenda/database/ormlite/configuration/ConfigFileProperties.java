package agenda.database.ormlite.configuration;

public class ConfigFileProperties {

    // Context
    public static final String CONTEXT_TAG = "context";
    public static final String CONTEXT_NAME_PROPERTY = "name";

    // Generic
    public static final String VALUE_PROPERTY = "value";

    // Connection
    public static final String URL_PROPERTY = "url";
    public static final String DRIVER_PROPERTY = "driver";
    public static final String USERNAME_PROPERTY = "username";
    public static final String PASSWORD_PROPERTY = "password";

    // Schema
    public static final String DDL_AUTO_PROPERTY = "ddl-auto";
    public static final String ROOT_PACKAGE_PROPERTY = "root-package";
    public static final String ENTITY_PACKAGE_PROPERTY = "entity-package";

    // Connection Pool
    public static final String POOL_SIZE_PROPERTY = "pool-size";
    public static final String CONNECTION_TIMEOUT_PROPERTY = "connection-timeout";

    // DDL Auto Values
    public static final String DDL_AUTO_CREATE_VALUE = "create";
    public static final String DDL_AUTO_UPDATE_VALUE = "update";
    public static final String DDL_AUTO_VALIDATE_VALUE = "validate";
    public static final String DDL_AUTO_CREATE_DROP_VALUE = "create-drop";
    public static final String DDL_AUTO_NONE_VALUE = "none";
}