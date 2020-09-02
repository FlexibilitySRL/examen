package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.ProductApi;

public interface ProductService {
	
	ProductApi create(ProductApi productApi);

	ProductApi get(Long id);

    List<ProductApi> getProducts();

    void delete(Long id);

    ProductApi update(Long id, ProductApi productApi);
}
