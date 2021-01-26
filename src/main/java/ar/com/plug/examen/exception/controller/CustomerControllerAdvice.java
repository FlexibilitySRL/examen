package ar.com.plug.examen.exception.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.plug.examen.exception.DeleteCustomerException;
import ar.com.plug.examen.exception.DuplicateCustomerException;
import ar.com.plug.examen.exception.NotCustomerFoundException;

@ControllerAdvice
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DuplicateCustomerException.class)
	protected ResponseEntity<Object> handleDuplicateCustomerException(DuplicateCustomerException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NotCustomerFoundException.class)
	protected ResponseEntity<Object> handleNotCustomerFoundException(NotCustomerFoundException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DeleteCustomerException.class)
	protected ResponseEntity<Object> handleDeleteCustomerException(DeleteCustomerException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
}
