package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PurchaseTransactionDoesNotExistErrorResponse extends ErrorResponse {
	@JsonProperty
	private long purchaseTransactionId;
	
	public PurchaseTransactionDoesNotExistErrorResponse(long purchaseTransactionId) {
		this.purchaseTransactionId = purchaseTransactionId;
	}
	
	protected PurchaseTransactionDoesNotExistErrorResponse() {
		
	}
	
	public long getPurchaseTransactionId() {
		return this.purchaseTransactionId;
	}
}
