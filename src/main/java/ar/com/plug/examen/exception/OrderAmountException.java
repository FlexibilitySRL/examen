package ar.com.plug.examen.exception;

public class OrderAmountException extends RuntimeException{

	public OrderAmountException() {
		super("the order amount is wrong. Does not match the subtotal of the products");
	}
}
