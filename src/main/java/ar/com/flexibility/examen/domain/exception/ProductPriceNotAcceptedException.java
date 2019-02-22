/**
 * 
 */
package ar.com.flexibility.examen.domain.exception;

/**
 * @author rosali zaracho
 *
 */
public class ProductPriceNotAcceptedException extends ExceptionGeneric implements ExceptionSystem  {

	private static final long serialVersionUID = 1L;

	public ProductPriceNotAcceptedException() {
		super("The price of product is mandatory and can not be negative or equal to zero");
	}

}
