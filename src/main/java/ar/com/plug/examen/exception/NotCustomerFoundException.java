package ar.com.plug.examen.exception;

public class NotCustomerFoundException extends RuntimeException {

	public NotCustomerFoundException(Long id) {

        super("No customer found for id " + id);
    }
	
	public NotCustomerFoundException(Long id,long documentId) {

        super("No customer found for id " + id + " document_id "+documentId) ;
    }
	
}
