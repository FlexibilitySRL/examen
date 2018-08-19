package ar.com.flexibility.examen.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entity conflict.")
public class EntityConflictException extends Exception {
    private static final long serialVersionUID = 665724235559771008L;

    public EntityConflictException(String message) {
        super(message);
    }
}
