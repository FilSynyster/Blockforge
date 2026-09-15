package agenda.database.core;

public interface PersistenceContext {

    /**
     * Encontra, implementa e registra subinterfaces de DataRepository que
     * representam repositórios de dados específicos de entidades podendo
     * utilizar vários tipos de provedores de dados como ORMLite e EclipseLink.
     * <br>
     * <br>
     * 1 - Localiza e implementa dinâmicamente subinterfaces de DataRepository.
     * <br>
     * 2 - Identifica a entidade no seu parâmetro genérico T.
     * <br>
     * 3 - Adiciona o repositório em um map com sua entidade sendo seu key.
     * <br>
     * */
    public void findCreateAndRegisterRepositories();
}
