package ar.com.plug.examen.exception;

public class DeleteSellerException extends RuntimeException {

	public DeleteSellerException(Long id) {

        super("The seller cannot be removed. It is associated with an order. Please delete the order first to delete the seller. seller id: " + id);
    }

}
