package ar.com.plug.examen.exception;

public class NotProductFoundException extends RuntimeException {

	public NotProductFoundException(Long id) {

        super("No product found for id " + id);
    }
	
}
