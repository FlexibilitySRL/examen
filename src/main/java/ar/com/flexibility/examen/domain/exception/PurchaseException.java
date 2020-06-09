package ar.com.flexibility.examen.domain.exception;

public class PurchaseException extends RuntimeException {

    public PurchaseException(String message){
        super(message);
    }

    public PurchaseException(String message, Throwable err){
        super(message, err);
    }
}
