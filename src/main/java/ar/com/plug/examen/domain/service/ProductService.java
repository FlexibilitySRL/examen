package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.ProductApi;

public interface ProductService {
	
	public ProductApi createProduct(ProductApi productApi);

	public ProductApi getProductById(Long id);
	
	public List<ProductApi> listAllProducts();
	
	void removeProduct(Long id);
	
	public ProductApi updateProduct(Long id, ProductApi productApi);

}
