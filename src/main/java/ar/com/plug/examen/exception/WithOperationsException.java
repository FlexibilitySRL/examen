package ar.com.plug.examen.exception;

public class WithOperationsException extends RuntimeException {
	public WithOperationsException() {
		super("Esta queriendo eliminar o modificar un objeto que fue utilizado en una operacion.");
	}

}
