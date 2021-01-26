package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Product;

public interface ProductService {

	public List<Product> getAllProduct();
	
	public Product getProductById(Long id);
	
	public void deleteProduct(Long id);
	
	public Product createProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public void validateProducts(List<Product> products);
	
}
