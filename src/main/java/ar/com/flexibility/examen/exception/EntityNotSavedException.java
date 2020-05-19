package ar.com.flexibility.examen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityNotSavedException extends GenericException {
	public EntityNotSavedException(String mensaje) {
		super(mensaje);
	}
}
