package ar.com.flexibility.examen.domain.build;

import ar.com.flexibility.examen.domain.model.Product;

public class ProductBuilder {

	private int amount;
	private String code;
	private String name;
	private double price;

	private ProductBuilder() {
		this.amount = 40;
		this.code = "PR7001";
		this.name = "ProductTest";
		this.price = 60.0;
	}

	public Product build() {
		Product product = new Product();
		
		product.setAmount(this.amount);
		product.setCode(this.code);
		product.setName(this.name);
		product.setPrice(this.price);
		
		return product;
	}

	public static ProductBuilder builder() {
		return new ProductBuilder();
	}

}
