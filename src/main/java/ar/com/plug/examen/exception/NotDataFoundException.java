package ar.com.plug.examen.exception;

public class NotDataFoundException  extends RuntimeException {

	public NotDataFoundException(Long id) {

        super("No data found for id " + id);
    }
	
}
