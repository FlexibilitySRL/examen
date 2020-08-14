package ar.com.flexibility.examen.app.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ar.com.flexibility.examen.exception.CreationException;
import ar.com.flexibility.examen.exception.DataValidationException;

@ControllerAdvice
public class CustomExceptionsController {

	@ExceptionHandler(value = DataValidationException.class)
	public ResponseEntity<Object> exception(DataValidationException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CreationException.class)
	public ResponseEntity<Object> exception(CreationException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
