package ar.com.flexibility.examen.domain.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.Product;

public final class ProductDTO {
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private BigDecimal unitPrice;
	
	public ProductDTO() {
		
	}
	
	/**
	 * @post Crea un DTO para el producto especificado
	 */
	public ProductDTO(Product product) {
		if ( product == null )
			throw new NullPointerException();
		
		this.name = product.getName();
		this.description = product.getDescription();
		this.unitPrice = product.getUnitPrice();
	}
	
	/**
	 * @post Convierte el DTO a entidad
	 */
	public Product toEntity() {
		Product product = new Product();
		
		this.updateEntity(product);
		
		return product;
	}
	
	/**
	 * @post Actualiza un producto
	 * @return
	 */
	public void updateEntity(Product product) {
		if ( product == null )
			throw new NullPointerException();
		
		product.setName(this.name);
		product.setDescription(this.description);
		product.setUnitPrice(this.unitPrice);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}
