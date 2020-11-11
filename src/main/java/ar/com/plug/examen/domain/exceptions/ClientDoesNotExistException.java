package ar.com.plug.examen.domain.exceptions;

public class ClientDoesNotExistException extends Exception {
    public ClientDoesNotExistException(String message) {
        super(message);
    }
}
