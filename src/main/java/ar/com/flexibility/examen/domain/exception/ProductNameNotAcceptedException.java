package ar.com.flexibility.examen.domain.exception;

public class ProductNameNotAcceptedException extends ExceptionGeneric implements ExceptionSystem  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNameNotAcceptedException() {
		super("The name of product is mandatory.");
	}

}
