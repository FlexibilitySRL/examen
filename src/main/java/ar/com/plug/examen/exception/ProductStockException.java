package ar.com.plug.examen.exception;

public class ProductStockException extends RuntimeException {

	public ProductStockException(String name) {
		super("the " + name + " quantity exceeds stock");
	}

}
