package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.db.Product;

public interface ProductService {
	/**
	 * Metodo que permite la creacion de un producto
	 * 
	 * @param product
	 * @return
	 */
	public Product createProduct(Product product);

	/**
	 * Metodo que permite actualizar informacion de un producto
	 * 
	 * @param product
	 * @return
	 */
	public Product updateProduct(Product product);

	/**
	 * Metodo que permite eliminar informacion de un producto
	 * 
	 * @param id
	 */
	public void deleteProduct(String id);

	/**
	 * Metodo que permite obtener informacion del producto por identicador
	 * 
	 * @param id
	 * @return
	 */
	Product getProductById(Long id);
}
