package ar.com.plug.examen.exception.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class DataIntegrityViolationExceptionAdvice {

  @ResponseBody
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String customerNotFoundHandler(DataIntegrityViolationException ex) {
    return "La operacion que esta realizando esta rompiendo la integridad de una relacion. Corrobore si esta eliminando un registro que el mismo no este referenciado. Si esta creando un objeto, corrobore que este no tenga una dependencia y esta no sea null";
  }
}
