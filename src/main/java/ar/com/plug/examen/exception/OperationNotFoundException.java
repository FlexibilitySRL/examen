package ar.com.plug.examen.exception;

public class OperationNotFoundException extends RuntimeException {
	public OperationNotFoundException(Long id) {
		super("Operation with ID: " + id + " not founded.");
	}

}
