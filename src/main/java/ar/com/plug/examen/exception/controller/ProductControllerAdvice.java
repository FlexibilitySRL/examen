package ar.com.plug.examen.exception.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.plug.examen.exception.DeleteProductException;
import ar.com.plug.examen.exception.DuplicateProductException;
import ar.com.plug.examen.exception.NotProductFoundException;
import ar.com.plug.examen.exception.ProductPriceException;
import ar.com.plug.examen.exception.ProductStockException;

@ControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DuplicateProductException.class)
	protected ResponseEntity<Object> handleDuplicateProductException(DuplicateProductException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NotProductFoundException.class)
	protected ResponseEntity<Object> handleNotProductFoundException(NotProductFoundException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductStockException.class)
	protected ResponseEntity<Object> handleProductStockException(ProductStockException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ProductPriceException.class)
	protected ResponseEntity<Object> handleProductPriceException(ProductPriceException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DeleteProductException.class)
	protected ResponseEntity<Object> handleDeleteProductException(DeleteProductException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
}
