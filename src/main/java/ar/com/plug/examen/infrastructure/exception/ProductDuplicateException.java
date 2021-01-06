package ar.com.plug.examen.infrastructure.exception;

public class ProductDuplicateException extends Exception {
    private static final long serialVersionUID = -5330068656795055851L;

    public ProductDuplicateException(String message) {
        super(message);
    }
}
