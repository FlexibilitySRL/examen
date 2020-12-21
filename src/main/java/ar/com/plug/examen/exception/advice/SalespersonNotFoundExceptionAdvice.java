package ar.com.plug.examen.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.plug.examen.exception.SalespersonNotFoundException;

@ControllerAdvice
class SalespersonNotFoundExceptionAdvice {

  @ResponseBody
  @ExceptionHandler(SalespersonNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String customerNotFoundHandler(SalespersonNotFoundException ex) {
    return ex.getMessage();
  }
}
