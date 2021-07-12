package ar.com.plug.examen.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GenericNotFoundException extends ResponseStatusException {

  public GenericNotFoundException() {
    super(HttpStatus.NOT_FOUND);
  }

  public GenericNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
