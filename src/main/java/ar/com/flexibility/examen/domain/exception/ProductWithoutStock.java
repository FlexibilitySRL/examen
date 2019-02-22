package ar.com.flexibility.examen.domain.exception;

public class ProductWithoutStock extends ExceptionGeneric implements ExceptionSystem  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public ProductWithoutStock() {
		super("Does not have enough stock");
	}

}
