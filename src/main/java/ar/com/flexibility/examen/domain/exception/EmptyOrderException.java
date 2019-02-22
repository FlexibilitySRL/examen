package ar.com.flexibility.examen.domain.exception;

public class EmptyOrderException extends ExceptionGeneric implements ExceptionSystem  {

	private static final long serialVersionUID = 1L;

	public EmptyOrderException() {
		super("The order is empty");
	}

}
