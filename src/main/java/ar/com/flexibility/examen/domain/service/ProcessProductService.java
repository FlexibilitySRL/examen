package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.ProductApi;

public interface ProcessProductService {

	ProductApi create(ProductApi productApi);
	
	ProductApi update(Long productId, ProductApi productApi);
	
	String delete(ProductApi productApi);
}
