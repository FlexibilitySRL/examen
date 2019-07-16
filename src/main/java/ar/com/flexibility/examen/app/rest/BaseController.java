package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.exception.FlexibilityException;
import ar.com.flexibility.examen.app.exception.FlexibilityNotFoundException;
import ar.com.flexibility.examen.domain.service.dto.ExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class BaseController {


    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(FlexibilityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDTO> exceptionHandler(FlexibilityNotFoundException e) {
        log.error("Error", e);
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlexibilityException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDTO> exceptionHandler(FlexibilityException e) {
        log.error("Error", e);
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionDTO> exceptionHandler(Exception e) {
        log.error("Error", e);
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
