package ar.com.plug.examen.exception;

public class NotCustomerFoundException extends RuntimeException {

	public NotCustomerFoundException(Long id) {

        super("No customer found for id " + id);
    }
	
}
