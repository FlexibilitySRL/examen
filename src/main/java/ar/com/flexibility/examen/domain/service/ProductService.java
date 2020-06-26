package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {
	
	List<Product> getAllProducts();

	Product getProductById(Long productId) ;

	Product save(Product product);

	void deleteProduct(Long productId) ;
	
}
