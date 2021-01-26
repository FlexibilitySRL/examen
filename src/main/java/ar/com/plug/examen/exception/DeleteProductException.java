package ar.com.plug.examen.exception;

public class DeleteProductException extends RuntimeException {

	public DeleteProductException(Long productId) {

        super("The product cannot be removed. It is associated with an order. Please delete the order first to delete the product. product id: " + productId);
    }

}
