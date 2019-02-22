/**
 * 
 */
package ar.com.flexibility.examen.domain.exception;

/**
 * @author rosali zaracho
 *
 */
public class ProductStockNotAcceptedException extends ExceptionGeneric implements ExceptionSystem  {

	private static final long serialVersionUID = 1L;

	public ProductStockNotAcceptedException() {
		super("The stock of product is mandatory and can not be negative or equal to zero");
	}

}
