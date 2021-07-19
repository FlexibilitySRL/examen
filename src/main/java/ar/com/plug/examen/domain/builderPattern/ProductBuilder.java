package ar.com.plug.examen.domain.builderPattern;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import ar.com.plug.examen.entities.Product;
import ar.com.plug.examen.entities.TransactionDetail;

public class ProductBuilder {
	
	private Product product;
	
	public ProductBuilder() {
		this.product = new Product();
	}
	
	public ProductBuilder withID(Long id) {
		this.product.setId(id);
		return this;
	}
	
	public ProductBuilder withName(String name) {
		this.product.setName(name);
		return this;
	}
	
	public ProductBuilder withPrice(Double price) {
		this.product.setPrice(price);
		return this;
	}
	
	public ProductBuilder withStock(int stock) {
		this.product.setStock(stock);
		return this;
	}
	
	public ProductBuilder withTransactionDetail(List<TransactionDetail> list) {
		this.product.setTransactionDetails(list);
		return this;
	}
	
	public Product build() {
		return new Product(this.product.getId(), this.product.getName(), this.product.getPrice(), this.product.getStock(), this.product.getTransactionDetails());
	}

}


