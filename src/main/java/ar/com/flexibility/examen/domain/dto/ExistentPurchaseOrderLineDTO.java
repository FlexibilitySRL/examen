package ar.com.flexibility.examen.domain.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ExistentPurchaseOrderLineDTO {
	@JsonProperty
	private long productId;
	 
	@JsonProperty
	private int quantity;
	
	@JsonProperty
	private BigDecimal unitPrice;
	 
	/**
	 * @post Crea un DTO con el id de producto, la cantidad, y el precio unitario especificados
	 */
	public ExistentPurchaseOrderLineDTO(long productId, int quantity, BigDecimal unitPrice) {
		this.productId = productId;
		this.quantity = quantity;
		
		if ( unitPrice != null ) {
			this.unitPrice = unitPrice;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	protected ExistentPurchaseOrderLineDTO() {
		
	}

	public long getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}
}
