package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Product;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
public interface ProcessProductService {

	
	/**
	 * This method return information about a product by its id.
	 * 
	 * @param id
	 * @return Clients.
	 */
	public Product getProductById(Long id);
	
	
	/**
	 * This method return all information about products.
	 * 
	 * @param id
	 * @return List<Clients>.
	 */
	public List<Product> getProducts();
	
	/**
	 * This method save a new product in database.
	 * 
	 * @return Boolean
	 */
	public Boolean saveProduct(Product product);
	
	/**
	 * This method update a new product in database by id.
	 * 
	 * @return Boolean
	 */
	public Boolean updateProduct(Product product, Long id);
	
	/**
	 * This method delete a new product in database by id.
	 * 
	 * @return Boolean
	 */
	public Boolean deleteProduct(Long id);
}
