package ar.com.plug.examen.domain.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorMessage> handleEmptyResultDataAccessException() {
        return ResponseEntity.badRequest().body(new ErrorMessage("error", "description"));
    }

}
