package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.rest.dto.ProductRequestDto;
import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {
	public void createProduct(ProductRequestDto productoDto);

	public void deleteProduct(Integer productId);

	public void updateProduct(ProductRequestDto dto);

	public Product findProduct(String productName);
}
