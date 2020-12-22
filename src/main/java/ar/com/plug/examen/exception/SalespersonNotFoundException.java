package ar.com.plug.examen.exception;

public class SalespersonNotFoundException extends RuntimeException {
	public SalespersonNotFoundException(Long id) {
		super("Salesperson con ID: " + id + " no fue encontrado.");
	}

}
