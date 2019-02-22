/**
 * 
 */
package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.Product;

/**
 * @author rosalizaracho
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class ProductApi {
	
	@JsonProperty
	private String name;
	@JsonProperty
	private Double price;
	@JsonProperty
	private Integer stock;
	@JsonProperty
	private Long idSeller;
	@JsonProperty
	private Long idProduct;
	
	public ProductApi() {}

	public ProductApi(Product p) {
		this.setName(p.getName());
		this.setPrice(p.getPrice());
		this.setStock(p.getStock());
		this.setIdSeller(p.getSeller().getIdSeller());
		this.setIdProduct(p.getIdProduct());
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(Long idSeller) {
		this.idSeller = idSeller;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
}
