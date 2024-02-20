package ar.com.plug.examen.domain.exception;

public class CartException extends Exception {
    public CartException() {
    }

    public CartException(String message) {
        super(message);
    }
}
