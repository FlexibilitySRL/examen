package ar.com.plug.examen.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class GenericBadRequestException extends ResponseStatusException {

    public GenericBadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public GenericBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
