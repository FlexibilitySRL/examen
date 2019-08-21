package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PurchaseOrderHasBeenApprovedErrorResponse extends ErrorResponse {
	@JsonProperty
	private long purchaseOrderId;
	
	public PurchaseOrderHasBeenApprovedErrorResponse(long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	protected PurchaseOrderHasBeenApprovedErrorResponse() {
		
	}
	
	public long getPurchaseOrderId() {
		return this.purchaseOrderId;
	}
	
}
