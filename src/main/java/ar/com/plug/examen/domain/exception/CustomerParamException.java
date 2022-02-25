package ar.com.plug.examen.domain.exception;

public class CustomerParamException extends RuntimeException {
    public CustomerParamException() {
        super();
    }

    public CustomerParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerParamException(String message) {
        super(message);
    }

    public CustomerParamException(Throwable cause) {
        super(cause);
    }
}
