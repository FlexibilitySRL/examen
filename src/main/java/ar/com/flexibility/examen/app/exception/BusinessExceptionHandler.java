package ar.com.flexibility.examen.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.flexibility.examen.domain.base.BaseResponse;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { BusinessException.class })
	protected ResponseEntity<?> handleBaseException(RuntimeException ex, WebRequest request) {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.BAD_REQUEST, ((BusinessException) ex).getMessages());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
