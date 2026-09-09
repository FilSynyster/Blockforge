package agenda.database.manager;

import agenda.database.JdbcRepository;
import agenda.database.JdbcRepositoryInvocationHandler;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    private ConnectionSource connectionSource;
    private ClassScanner scanner;
    private Map<Class<?>, JdbcRepository> repositoryRegistry = new HashMap<>();

    public DatabaseManager(String packageName, String url) {
        scanner = new ClassScanner(packageName);
        try {
            connectionSource = new JdbcConnectionSource(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    /**
     * Implementa usando Proxy subinterfaces de JdbcRepository,
     * recupera seu primeiro parâmetro genérico que é a entidade do repositório
     * e registra o repositório em um map com sua entidade sendo seu key.
     *
     * */
    private void createAndRegisteRepository(Class<? extends JdbcRepository<?,?>> repositoryClass) {

        //Recupera o tipo do primeiro parâmetro genérico que é a entidade
        ParameterizedType type = (ParameterizedType) repositoryClass.getGenericInterfaces()[0];
        Type[] argTypes = type.getActualTypeArguments();
        Class<?> entity = (Class<?>) argTypes[0];

        //Cria a implementação dinâmica de JdbcRepository
        ClassLoader classLoader = repositoryClass.getClassLoader();
        Class<?>[] interfaces = { repositoryClass };
        JdbcRepository<?,?> rep =  new SqliteRepositoryImpl(connectionSource, entity);
        JdbcRepository<?,?> repositoryImpl = (JdbcRepository<?,?>) Proxy.newProxyInstance(
                classLoader,
                interfaces,
                new JdbcRepositoryInvocationHandler<>(rep)
        );

        repositoryRegistry.put(entity, repositoryImpl);
    }

    /**
     * Encontra, implementa e registra subinterfaces de JdbcRepository que
     * representam repositórios de dados específicos de entidades.
     * <br>
     * <br>
     * 1 - Localiza e implementa dinâmicamente subinterfaces de JdbcRepository.
     * <br>
     * 2 - Identifica a entidade no seu parâmetro genérico T.
     * <br>
     * 3 - Adiciona o repositório em um map com sua entidade sendo seu key.
     * <br>
     * */
    public void findCreateAndRegisterRepositories() {
        List<Class<?>> classes = scanner.findSubclasses(JdbcRepository.class);
        for (Class<?> repositoryClass : classes ) {
            createAndRegisteRepository((Class<? extends JdbcRepository<?, ?>>) repositoryClass);
        }
    }

    public <T> T getRepository(Class<?> entityClass) {
        return (T) repositoryRegistry.get(entityClass);
    }

    public void closeConnection() {
        try {
            connectionSource.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
