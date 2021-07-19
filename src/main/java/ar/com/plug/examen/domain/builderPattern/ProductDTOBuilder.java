package ar.com.plug.examen.domain.builderPattern;

import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.entities.Product;

public class ProductDTOBuilder {

	private ProductDTO product;
	
	public ProductDTOBuilder() {
		this.product = new ProductDTO();
	}
	
	public ProductDTOBuilder withID(Long id) {
		this.product.setId(id);
		return this;
	}
	
	public ProductDTOBuilder withName(String name) {
		this.product.setName(name);
		return this;
	}
	
	public ProductDTOBuilder withPrice(Double price) {
		this.product.setPrice(price);
		return this;
	}
	
	public ProductDTOBuilder withStock(int stock) {
		this.product.setStock(stock);
		return this;
	}
	
	public ProductDTO build() {
		return new ProductDTO(this.product.getId(), this.product.getName(), this.product.getPrice(), this.product.getStock());
	}
}
