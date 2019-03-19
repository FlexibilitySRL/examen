package ar.com.flexibility.examen.domain.model.exception;

public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 1139993851537212947L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
