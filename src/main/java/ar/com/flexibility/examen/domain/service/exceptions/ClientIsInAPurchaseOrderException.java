package ar.com.flexibility.examen.domain.service.exceptions;

public final class ClientIsInAPurchaseOrderException extends BusinessException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8791323409134923413L;
	
	private long clientId;
	
	public ClientIsInAPurchaseOrderException(long clientId) {
		this.clientId = clientId;
	}
	
	public long getClientId() {
		return this.clientId;
	}

	@Override
	public <R> R accept(BusinessExceptionVisitor<R> visitor) {
		return visitor.visit(this);
	}
}
