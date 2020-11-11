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
    Product save(Product aProduct);

    /**
     * Find a product by id
     * @param id
     * @return aProduct
     */
    Product findById(Long id);

    /**
     * Delete the product
     * @param aProduct
     */
    void delete(Product aProduct);

    /**
     * Method to update a product
     * @param aProduct
     * @return
     */
    Product updateProduct(Product aProduct);
}
