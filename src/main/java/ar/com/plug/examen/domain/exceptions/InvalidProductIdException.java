package ar.com.plug.examen.domain.exceptions;

public class InvalidProductIdException extends Exception {
    public InvalidProductIdException(String message) {
        super(message);
    }
}
