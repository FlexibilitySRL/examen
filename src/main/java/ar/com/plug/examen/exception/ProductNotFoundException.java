package ar.com.plug.examen.exception;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(Long id) {
		super("Customer with ID: " + id + " not founded.");
	}

}
