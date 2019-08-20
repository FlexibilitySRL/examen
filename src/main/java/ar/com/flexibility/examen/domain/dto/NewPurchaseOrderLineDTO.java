package ar.com.flexibility.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class NewPurchaseOrderLineDTO {
	@JsonProperty
	private long productId;
	 
	@JsonProperty
	private int quantity;
	 
	/**
	 * @post Crea un DTO con el id de producto y la cantidad
	 */
	public NewPurchaseOrderLineDTO(long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}
	
	protected NewPurchaseOrderLineDTO() {
		
	}

	public long getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
	 
}
