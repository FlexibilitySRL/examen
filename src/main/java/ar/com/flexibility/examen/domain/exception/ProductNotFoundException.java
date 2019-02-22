/**
 * 
 */
package ar.com.flexibility.examen.domain.exception;

/**
 * @author ro
 *
 */
public class ProductNotFoundException extends ExceptionGeneric implements ExceptionSystem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ProductNotFoundException() {
		super("Produt not found");
	}

}
