package ar.com.plug.examen.exception.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.plug.examen.exception.DeleteSellerException;
import ar.com.plug.examen.exception.DuplicateSellerException;
import ar.com.plug.examen.exception.NotSellerFoundException;

@ControllerAdvice
public class SellerControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotSellerFoundException.class)
	protected ResponseEntity<Object> handleNotSellerFoundException(NotSellerFoundException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateSellerException.class)
	protected ResponseEntity<Object> handleDuplicatSellerException(DuplicateSellerException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DeleteSellerException.class)
	protected ResponseEntity<Object> handleDeleteSellerException(DeleteSellerException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
}
