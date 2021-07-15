package ar.com.plug.examen.domain.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceInternalServerError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ErrorDTO;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler({ResourceInternalServerError.class})
	public ResponseEntity<ErrorDTO> handleInternalServerError(ResourceInternalServerError ex, WebRequest request) {
		ErrorDTO error = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ResourceNotFoundError.class})
	public ResponseEntity<ErrorDTO> handleANotFoundError(ResourceNotFoundError ex, WebRequest request) {
		ErrorDTO error = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({BadRequestError.class})
	public ResponseEntity<ErrorDTO> handleBadRequestError(BadRequestError ex, WebRequest request) {
		ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
