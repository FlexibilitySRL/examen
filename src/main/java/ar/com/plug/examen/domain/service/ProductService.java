package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Product;

public interface ProductService {

	Product create(Product product);
	Product update(Long id, Product product);
	void delete(Long id);
	List<Product> getProducts();
	Optional<Product> getProductById(Long id);
}
