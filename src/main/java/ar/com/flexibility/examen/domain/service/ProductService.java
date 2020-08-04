package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {
	
	void cleanSales (Product entity);

	void deleteProduct(String code) throws ServiceException;

	Product getProduct(String code) throws ServiceException;

	List<Product> list() throws ServiceException;

	void newProduct(String code, String name, int amount, double price) throws ServiceException;

	void updateProduct(String code, String newCode, String name, int amount, double price) throws ServiceException;
	
	void updateProductAmount(Product entity, int amount) throws ServiceException;

}
