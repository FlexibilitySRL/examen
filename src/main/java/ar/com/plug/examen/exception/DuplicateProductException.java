package ar.com.plug.examen.exception;

public class DuplicateProductException  extends RuntimeException {

	public DuplicateProductException(Long productId) {

        super("duplicate id " + productId);
    }


}
