package ar.com.flexibility.examen.domain.service.impl;

public class ProductExistsException extends Exception {

    public ProductExistsException() {
        super("The Product already exist");
    }

}
