package ar.com.plug.examen.exception;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(Long id) {
		super("Product con ID: " + id + " no fue encontrado.");
	}

}
