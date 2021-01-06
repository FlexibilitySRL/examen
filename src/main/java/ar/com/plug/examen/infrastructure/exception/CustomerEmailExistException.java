package ar.com.plug.examen.infrastructure.exception;

public class CustomerEmailExistException extends Exception {
    private static final long serialVersionUID = -5330068136795055851L;

    public CustomerEmailExistException(String message) {
        super(message);
    }
}
