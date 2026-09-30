package agenda.database.exceptions;

public class RepositoryQueryNotFoundException extends RuntimeException {

    public RepositoryQueryNotFoundException(String message) {
        super(message);
    }
}
