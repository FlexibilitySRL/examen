package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {

	public Product createProduct(Product product);
	
	public Product findProductById(Long id);
	
	public List<Product> findAllProducts();
	
	public Product updateProduct(Product product);
	
	public Product deleteProduct(Product product);
	
}