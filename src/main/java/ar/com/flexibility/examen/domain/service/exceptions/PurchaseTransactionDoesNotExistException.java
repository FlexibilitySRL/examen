package ar.com.flexibility.examen.domain.service.exceptions;

public final class PurchaseTransactionDoesNotExist extends UserServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6724345888521454952L;
	
	private long purchaseTransactionId;

	public PurchaseTransactionDoesNotExist(long purchaseTransactionId) {
		this.purchaseTransactionId = purchaseTransactionId;
	}
	
	public long getPurchaseTransactionId() {
		return purchaseTransactionId;
	}
}
