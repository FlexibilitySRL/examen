package ar.com.plug.examen.exception;

public class OperationNotFoundException extends RuntimeException {
	public OperationNotFoundException(Long id) {
		super("Operation con ID: " + id + " no fue encontrado.");
	}

}
