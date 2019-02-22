package ar.com.flexibility.examen.domain.exception;

public class SellerNotFoundException extends ExceptionGeneric implements ExceptionSystem {

	private static final long serialVersionUID = 1L;

	public SellerNotFoundException() {
		super("Seller not found");
	}
}
