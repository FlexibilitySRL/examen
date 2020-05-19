package ar.com.flexibility.examen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends GenericException {
	public EntityNotFoundException(String mensaje) {
		super(mensaje);
	}
}
