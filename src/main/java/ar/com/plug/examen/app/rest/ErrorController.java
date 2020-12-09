package ar.com.plug.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;

@ControllerAdvice
public class ErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(ValidatorException.class)
	public ResponseEntity<String> handleTrackerException(ValidatorException e){
    	logger.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
    
    @ExceptionHandler(NotExistException.class)
  	public ResponseEntity<String> handleTrackerException(NotExistException e){
      	logger.error(e.getMessage());
  		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  	}


}
