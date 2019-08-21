package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ProductDoesNotExistErrorResponse extends ErrorResponse {
	@JsonProperty
	private long productId;
	
	public ProductDoesNotExistErrorResponse(long productId) {
		this.productId = productId;
	}
	
	protected ProductDoesNotExistErrorResponse() {
		
	}
	
	public long getProductId() {
		return this.productId;
	}
}
