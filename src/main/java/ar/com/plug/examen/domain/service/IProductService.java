package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.model.Product;

import java.util.List;

public interface IProductService {
    /**
     * List all the products on the DB.
     * @return List<Product>
     */
    List<Product> findAll();

    /**
     * Save the product passed by param
     * @param aProduct
     * @return aProduct
     */
    Product saveProduct(Product aProduct);

    /**
     * Find a product by id
     * @param id
     * @return aProduct
     */
    Product findById(Long id) throws ProductDoesNotExistException;

    /**
     * Delete the product
     * @param id
     */
    void deleteProduct(Long id) throws ProductDoesNotExistException;

    /**
     * Method to update a product
     * @param aProduct
     * @return
     */
    Product updateProduct(Product aProduct) throws ProductDoesNotExistException;
}
