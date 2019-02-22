package ar.com.flexibility.examen.domain.exception;

public class ClientNotFoundException extends ExceptionGeneric implements ExceptionSystem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException() {
		super("Client not found");
	}



}
