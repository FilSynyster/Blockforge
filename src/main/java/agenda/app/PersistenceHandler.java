package agenda.app;

import agenda.database.core.DataRepository;
import agenda.database.core.PersistenceContextFactory;
import agenda.database.ormlite.OrmlitePersistenceContext;

public final class PersistenceHandler {



    private PersistenceHandler() {
    }

    private static final OrmlitePersistenceContext CONTEXT;

    static {
        PersistenceContextFactory persist = new PersistenceContextFactory();
        CONTEXT = (OrmlitePersistenceContext) persist.createPersistenceContext("agenda-persistence");
        CONTEXT.findCreateAndRegisterRepositories();
    }

    public static <R extends DataRepository<?,?>> R getRepository(Class<?> entity) {
        return CONTEXT.getRepositoryRegistry().getRepository(entity);
    }

}
