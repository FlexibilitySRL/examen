package ar.com.flexibility.examen.app.service;

import javax.persistence.EntityNotFoundException;

import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

	Iterable<Product> getAllProducts();

	Page<Product> getProducts(Pageable pageable);

	Product getProductById(Long productId) throws EntityNotFoundException;

	Product createProduct(Product product);

	String deleteProduct(Long productId) throws EntityNotFoundException;

}
