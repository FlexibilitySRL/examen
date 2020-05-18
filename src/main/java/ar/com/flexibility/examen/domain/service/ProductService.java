package ar.com.flexibility.examen.domain.service;

import org.springframework.http.ResponseEntity;

import ar.com.flexibility.examen.domain.model.Product;


public interface ProductService {

	ResponseEntity<?> getProducts();
	
	ResponseEntity<?> getProductById(Long id);
    
	ResponseEntity<?> insertProduct(Product product);
    
	ResponseEntity<?> updateProduct(Long id, Product product);
    
	ResponseEntity<?> deleteProduct(Long id);
}
