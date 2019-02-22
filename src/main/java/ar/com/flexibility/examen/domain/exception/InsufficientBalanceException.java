package ar.com.flexibility.examen.domain.exception;


public class InsufficientBalanceException extends ExceptionGeneric implements ExceptionSystem {
	
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {
		super("Does not have enough balance");
	}
}
