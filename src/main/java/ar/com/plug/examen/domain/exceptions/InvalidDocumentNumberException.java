package ar.com.plug.examen.domain.exceptions;

public class InvalidDocumentNumber extends Exception {
    public InvalidDocumentNumber(String message) {
        super((message));
    }
}
