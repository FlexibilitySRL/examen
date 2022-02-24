package ar.com.plug.examen.domain.exception;

public class ProductParamException extends RuntimeException{
    public ProductParamException() {
        super();
    }

    public ProductParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductParamException(String message) {
        super(message);
    }

    public ProductParamException(Throwable cause) {
        super(cause);
    }
}
