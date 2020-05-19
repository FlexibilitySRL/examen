package ar.com.flexibility.examen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityNotUpdatedException extends GenericException {
	public EntityNotUpdatedException(String mensaje) {
		super(mensaje);
	}
}
