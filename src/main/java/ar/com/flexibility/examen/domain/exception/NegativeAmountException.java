package ar.com.flexibility.examen.domain.exception;

public class NegativeAmountException extends ExceptionGeneric implements ExceptionSystem {
	
	private static final long serialVersionUID = 1L;

	public NegativeAmountException() {
		super("The amount can not be negative or equal to zero");	
	}
}
