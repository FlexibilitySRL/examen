package ar.com.plug.examen.app.rest;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.plug.examen.domain.service.impl.AlreadyReportedException;
import ar.com.plug.generated.model.Error;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public RestResponseEntityExceptionHandler() {
		super();
	}

	@ExceptionHandler(AlreadyReportedException.class)
	public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
		Error err = new Error().code(HttpStatus.BAD_REQUEST.value()).message("Already decided over this purchase transaction");
		return new ResponseEntity<Object>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleNotFound(final EntityNotFoundException ex, final WebRequest request) {
		Error err = new Error().code(HttpStatus.NOT_FOUND.value()).message(ex.getLocalizedMessage());
		return new ResponseEntity<Object>(err, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}