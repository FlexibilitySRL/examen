package ar.com.flexibility.examen.domain.service.exceptions;

public final class UnexpectedNullValueException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1514390432466851119L;

	@Override
	public <R> R accept(BusinessExceptionVisitor<R> visitor) {
		return visitor.visit(this);
	}
}
