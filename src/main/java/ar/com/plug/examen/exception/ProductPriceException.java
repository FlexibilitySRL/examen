package ar.com.plug.examen.exception;

public class ProductPriceException extends RuntimeException {

	public ProductPriceException(String name) {
		super("the price of " + name + " the  is wrong;");
	}
}
