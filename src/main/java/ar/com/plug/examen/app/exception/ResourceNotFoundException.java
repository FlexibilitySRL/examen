package ar.com.plug.examen.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = -4616998766782622295L;

	public ResourceNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

}
