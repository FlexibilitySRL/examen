package ar.com.plug.examen.exception;

public class NotSellerFoundException extends RuntimeException {

	public NotSellerFoundException(Long id) {
		super("seller not found with id " + id);
	}
}
