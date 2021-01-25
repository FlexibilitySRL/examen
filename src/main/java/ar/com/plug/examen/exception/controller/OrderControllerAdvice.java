package ar.com.plug.examen.exception.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.plug.examen.exception.NotOrderFoundException;
import ar.com.plug.examen.exception.OrderAmountException;
import ar.com.plug.examen.exception.OrderStatusException;

@ControllerAdvice
public class OrderControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OrderAmountException.class)
	protected ResponseEntity<Object> handleOrderAmountException(OrderAmountException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(OrderStatusException.class)
	protected ResponseEntity<Object> handleOrderStatusException(OrderStatusException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NotOrderFoundException.class)
	protected ResponseEntity<Object> handleNotOrderFoundException(NotOrderFoundException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
