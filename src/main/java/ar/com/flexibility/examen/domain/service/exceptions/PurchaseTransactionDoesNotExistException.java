package ar.com.flexibility.examen.domain.service.exceptions;

public final class PurchaseTransactionDoesNotExistException extends BusinessException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6724345888521454952L;
	
	private long purchaseTransactionId;

	public PurchaseTransactionDoesNotExistException(long purchaseTransactionId) {
		this.purchaseTransactionId = purchaseTransactionId;
	}
	
	public long getPurchaseTransactionId() {
		return purchaseTransactionId;
	}

	@Override
	public <R> R accept(BusinessExceptionVisitor<R> visitor) {
		return visitor.visit(this);
	}
}
