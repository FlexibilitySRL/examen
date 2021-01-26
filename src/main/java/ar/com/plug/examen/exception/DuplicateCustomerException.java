package ar.com.plug.examen.exception;

public class DuplicateCustomerException  extends RuntimeException {

	public DuplicateCustomerException(Long documentId) {

        super("duplicate document_id " + documentId);
    }

}
