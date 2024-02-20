package ar.com.plug.examen.domain.exception;

public class OrderException extends Exception {
    public OrderException() {
    }

    public OrderException(String message) {
        super(message);
    }
}
