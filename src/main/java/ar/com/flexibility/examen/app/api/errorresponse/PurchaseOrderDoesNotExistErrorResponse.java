package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PurchaseOrderDoesNotExistErrorResponse extends ErrorResponse {
	@JsonProperty
	private long purchaseOrderId;
	
	public PurchaseOrderDoesNotExistErrorResponse(long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	protected PurchaseOrderDoesNotExistErrorResponse() {
		
	}
	
	public long getPurchaseOrderId() {
		return this.purchaseOrderId;
	}
}
