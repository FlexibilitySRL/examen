package ar.com.flexibility.examen.domain.service.exceptions;

public abstract class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3613925849893138323L;
	
	public abstract <R> R accept(BusinessExceptionVisitor<R> visitor);
}
