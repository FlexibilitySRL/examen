package ar.com.plug.examen.exception;

public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(Long id) {
		super("Product with ID: " + id + " not founded.");
	}

}
