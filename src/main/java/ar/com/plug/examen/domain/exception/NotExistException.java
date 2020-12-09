package ar.com.plug.examen.domain.exception;

public class NotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2931260013551460999L;

	public NotExistException(String message) {
		super(message);
	}
}
