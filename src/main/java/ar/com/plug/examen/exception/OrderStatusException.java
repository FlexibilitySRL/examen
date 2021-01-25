package ar.com.plug.examen.exception;

public class OrderStatusException extends RuntimeException {

	public OrderStatusException() {
		super("the status of the order prevents executing this action");
	}
}
