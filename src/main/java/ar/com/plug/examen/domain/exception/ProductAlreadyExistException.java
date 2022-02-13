package ar.com.plug.examen.domain.exception;

public class ProductAlreadyExistException extends RuntimeException {

    public ProductAlreadyExistException() {
        super("There is already a product with that description");
    }
}
