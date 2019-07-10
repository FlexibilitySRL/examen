package ar.com.flexibility.examen.domain.service.exception;

public class ProductExistsException extends Exception {

    public ProductExistsException() {
        super("The Product already exist");
    }

}
