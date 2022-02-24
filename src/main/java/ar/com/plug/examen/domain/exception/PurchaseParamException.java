package ar.com.plug.examen.domain.exception;

public class PurchaseParamException extends RuntimeException{
    public PurchaseParamException() {
        super();
    }

    public PurchaseParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchaseParamException(String message) {
        super(message);
    }

    public PurchaseParamException(Throwable cause) {
        super(cause);
    }
}
