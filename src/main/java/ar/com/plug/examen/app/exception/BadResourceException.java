package ar.com.plug.examen.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadResourceException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5619770147882766460L;

	public BadResourceException(String message, Throwable cause) {
		super(HttpStatus.BAD_REQUEST, message, cause);
	}

	public BadResourceException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}


}
