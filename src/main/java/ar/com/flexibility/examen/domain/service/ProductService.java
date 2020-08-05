package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.response.ProductApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {

	void deleteProduct(String code) throws ServiceException;

	ProductApiResponse getProduct(String code) throws ServiceException;
	
	Product getEntity (String identifier) throws ServiceException;

	List<ProductApiResponse> list() throws ServiceException;

	void newProduct(String code, String name, int amount, double price) throws ServiceException;

	void updateProduct(String code, String newCode, String name, int amount, double price) throws ServiceException;
	
	void updateProductAmount(Product entity, int amount) throws ServiceException;

}
