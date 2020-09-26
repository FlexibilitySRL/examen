package ar.com.flexibility.examen.exception;

public class InvoiceException extends RuntimeException {
	private static final long serialVersionUID = 8903249883775222376L;

	/**
	 * @param message
	 * @param cause
	 */
	public InvoiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public InvoiceException(String message) {
		super(message);
	}
}
