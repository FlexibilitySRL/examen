package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ProductIsInAPurchaseOrderErrorResponse extends ErrorResponse {
	@JsonProperty
	private long productId;
	
	public ProductIsInAPurchaseOrderErrorResponse(long productId) {
		this.productId = productId;
	}
	
	protected ProductIsInAPurchaseOrderErrorResponse() {
		
	}
	
	public long getProductId() {
		return this.productId;
	}
}
