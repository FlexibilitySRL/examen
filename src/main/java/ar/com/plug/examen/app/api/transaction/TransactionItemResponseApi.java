package ar.com.plug.examen.app.api.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.plug.examen.app.api.product.ProductResponseApi;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionItemResponseApi {

	@JsonProperty
	private Long id;

	@JsonProperty
	private ProductResponseApi product;

	@JsonProperty
	private Long quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductResponseApi getProduct() {
		return product;
	}

	public void setProduct(ProductResponseApi product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
