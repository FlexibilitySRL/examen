package ar.com.flexibility.examen.exception;

/**
 * 
 * @author JUANK
 *
 */
public class ProductException extends RuntimeException {

	private static final long serialVersionUID = 8903249883775222376L;

	/**
	 * @param message
	 * @param cause
	 */
	public ProductException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ProductException(String message) {
		super(message);
	}

}
