package ar.com.plug.examen.infrastructure.exception;

public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = -533006813673455851L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
