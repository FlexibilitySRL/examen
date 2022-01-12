package ar.com.plug.examen.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConflictResourceException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5619770147882766460L;


	public ConflictResourceException(String message) {
		super(HttpStatus.CONFLICT, message);
	}


}
