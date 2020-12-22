package ar.com.plug.examen.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.plug.examen.exception.WithOperationsException;

@ControllerAdvice
public class WithOperationsExceptionAdvice {
	@ResponseBody
	@ExceptionHandler(WithOperationsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String customerNotFoundHandler(WithOperationsException ex) {
		return ex.getMessage();
	}
}


