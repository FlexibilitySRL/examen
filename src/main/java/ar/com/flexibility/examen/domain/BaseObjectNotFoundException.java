package ar.com.flexibility.examen.domain;

/**
 * Exception for non existent BaseObjects
 */
public class BaseObjectNotFoundException extends Exception{

	private static final long serialVersionUID = -1908830443834965898L;

	public BaseObjectNotFoundException(String message) {
		super(message);
	}

	public BaseObjectNotFoundException() {
		super();
	}
}
