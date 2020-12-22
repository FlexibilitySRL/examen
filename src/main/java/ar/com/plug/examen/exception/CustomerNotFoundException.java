package ar.com.plug.examen.exception;

public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(Long id) {
		super("Product con ID: " + id + " no fue encontrado.");
	}

}
