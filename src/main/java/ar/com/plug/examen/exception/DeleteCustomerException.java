package ar.com.plug.examen.exception;

public class DeleteCustomerException extends RuntimeException {

	public DeleteCustomerException(Long id) {

        super("The customer cannot be removed. It is associated with an order. Please delete the order first to delete the customer. customer id: " + id);
    }

}
