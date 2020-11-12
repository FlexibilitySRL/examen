package ar.com.plug.examen.domain.exceptions;

public class InvalidDocumentNumberException extends Exception {
    public InvalidDocumentNumberException(String message) {
        super((message));
    }
}
