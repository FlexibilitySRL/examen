package ar.com.plug.examen.exception;

public class NotOrderFoundException extends RuntimeException {

	public NotOrderFoundException(Long id) {
		super("Order not found with id " + id);
	}

}
