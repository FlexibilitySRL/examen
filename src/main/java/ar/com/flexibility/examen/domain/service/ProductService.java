package ar.com.flexibility.examen.domain.service;


import ar.com.flexibility.examen.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    /**
     * Crear un producto
     * @param product a crear
     * @return product creado
     */
	public Product create(Product product);
    
	/**
	 * Actualiza un producto
	 * @param product a actualizar
	 * @return Product actualizado
	 */
	public Product update(Product product);
    
	/**
     * Retorna un producto en base al id
     * @param id
     * @return Product
     */
	public Product findById(Long id);
    
	/**
     * Retorna todos los productos
     * @return List<Product>
     */
	public List<Product> findAll();
    
	/**
	 * Borra un producto en base al id
	 * @param id
	 */
	public void deleteById(Long id);
}
