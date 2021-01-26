package ar.com.plug.examen.exception;

public class DuplicateSellerException  extends RuntimeException {

	public DuplicateSellerException(Long documentId) {

        super("duplicate document_id " + documentId);
    }

}
