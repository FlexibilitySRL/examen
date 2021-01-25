package ar.com.plug.examen.exception;

public class NotSellerFoundException extends RuntimeException {

	public NotSellerFoundException(Long id) {
		super("seller not found with id " + id);
	}
	
	public NotSellerFoundException(Long id,long documentId) {
		super("seller not found with id " + id + " and document_id "+ documentId);
	}
}
