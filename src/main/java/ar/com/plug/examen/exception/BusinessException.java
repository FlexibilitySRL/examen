package ar.com.plug.examen.exception;

import org.springframework.core.NestedRuntimeException;

public class BusinessException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);
	}

}
