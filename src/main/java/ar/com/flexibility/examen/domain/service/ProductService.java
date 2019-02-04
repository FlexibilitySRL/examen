package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Product;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
public interface ProductService {
	
	/**
	 * Create a Product
	 * 
	 * @param product
	 * @return Product
	 */
	public Product create(Product product);
	
	/**
	 * Update a Product
	 * 
	 * @param product
	 * @return Product
	 */
	public Product update(Product product);
	
	/**
	 * Returns a Product by ID
	 * 
	 * @param idProduct
	 * @return Product
	 */
	public Product findById(Long idProduct);
	
	/**
	 * Returns a list of all products
	 * 
	 * @return List<Product>
	 */
	public List<Product> findAll();
	
	/**
	 * Delete a Product by ID
	 * 
	 * @param idProduct
	 */
	public void deleteById(Long idProduct);
}
