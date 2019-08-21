package ar.com.flexibility.examen.domain.service.exceptions;

public final class PurchaseOrderHasBeenApprovedException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8678023229998059501L;
	
	private long purchaseOrderId;
	
	public PurchaseOrderHasBeenApprovedException(long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	public long getPurchaseOrderId() {
		return this.purchaseOrderId;
	}

	@Override
	public <R> R accept(BusinessExceptionVisitor<R> visitor) {
		return visitor.visit(this);
	}
}
