package ar.com.plug.examen.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;


@ControllerAdvice
@Component
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Client Not Found", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ClientFoundException.class)
	public ResponseEntity<Object> handleClientFoundException(ClientFoundException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.FOUND.name(), "Client Found", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.FOUND);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Product Not Found", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductBadRequestException.class)
	public ResponseEntity<Object> handleProductBadRequestException(ProductBadRequestException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Product Bad Request", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ClientBadRequestException.class)
	public ResponseEntity<Object> handleClientBadRequestException(ClientBadRequestException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Client Bad Request", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SellerNotFoundException.class)
	public ResponseEntity<Object> handleSellerNotFoundException(SellerNotFoundException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Seller Not Found", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SellerFoundException.class)
	public ResponseEntity<Object> handleSellerFoundException(SellerFoundException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.FOUND.name(), "Seller Found", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.FOUND);
	}

	@ExceptionHandler(SellerBadRequestException.class)
	public ResponseEntity<Object> handleSellerBadRequestException(SellerBadRequestException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Seller Bad Request", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	private List<String> getDetails(Exception e) {
		return Arrays.asList(e.getLocalizedMessage());
	}

}
