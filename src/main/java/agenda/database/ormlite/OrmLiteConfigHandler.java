package agenda.database.ormlite;

import agenda.database.ormlite.configuration.ConfigFileProperties;
import agenda.database.core.ClassScanner;
import agenda.database.ormlite.configuration.ContextConfig;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class OrmLiteConfigHandler {

    private ConnectionSource connectionSource;
    private ClassScanner scanner;
    private String databaseUrl;


    public OrmLiteConfigHandler(ClassScanner classScanner, String databaseUrl) {
        this.scanner = classScanner;
        this.databaseUrl = databaseUrl;
        config();
    }

    public void config() {
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        createTablesIfNotExists();
    }

    /**
     * Localiza entidades (classes) anotadas com @DatabaseTable e
     * utiliza seu Class para criar as tabelas que elas representam
     * caso elas não existam.
     * <br>
     * <br>
     * 1 - Procura por classes anotadas com @DatabaseTable.
     * <br>
     * 2 - Utiliza TableUtils.createTableIfNotExists(ConnectionSource connectionSource, Class<T> dataClass) para criar as tabelas.
     *
     * <br>
     * <br>
     * <b>Obs: Falta implementar um grafo para ordenar a criação das tabelas seguindo suas relações.<b/>
     * */
    public void createTablesIfNotExists() {
        try {
            List<Class<?>> classes = scanner.findClassesWithAnnotation(DatabaseTable.class);
            for ( Class<?> entityClass : classes ) {
                TableUtils.createTableIfNotExists(connectionSource, entityClass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public OrmliteRepository<?,?> createOrmliteRepository(Class<?> entityClass) {
        return new OrmliteRepository<>(connectionSource, entityClass);
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
