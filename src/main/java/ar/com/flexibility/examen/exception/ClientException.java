package ar.com.flexibility.examen.exception;

public class ClientException  extends RuntimeException {
	private static final long serialVersionUID = 8903249883775222376L;

	/**
	 * @param message
	 * @param cause
	 */
	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ClientException(String message) {
		super(message);
	}
}
