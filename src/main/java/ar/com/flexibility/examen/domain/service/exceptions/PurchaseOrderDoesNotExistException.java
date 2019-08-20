package ar.com.flexibility.examen.domain.service.exceptions;

public final class PurchaseOrderDoesNotExistException extends UserServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1360033346802034574L;

	private long purchaseOrderId;
	
	public PurchaseOrderDoesNotExistException(long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	/**
	 * @post Devuelve el id de órden de compra
	 */
	public long getPurchaseOrderId() {
		return this.purchaseOrderId;
	}
}
