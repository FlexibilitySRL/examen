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
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<Object> handleAccountNotFoundException(ClientException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Client Not Found", getDetails(e));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	private List<String> getDetails(Exception e) {
		return Arrays.asList(e.getLocalizedMessage());
	}

}
