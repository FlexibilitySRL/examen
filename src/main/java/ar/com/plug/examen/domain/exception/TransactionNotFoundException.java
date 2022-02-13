package ar.com.plug.examen.domain.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super("Transaction not found");
    }
}
