package ar.com.plug.examen.domain.exception;

public class CustomerException extends Exception {
    public CustomerException() {
    }

    public CustomerException(String message) {
        super(message);
    }
}
