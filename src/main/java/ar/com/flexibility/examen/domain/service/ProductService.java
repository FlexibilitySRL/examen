package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.ProductApi;

import java.util.List;

public interface ProductService {

    /**
     * Create a new product entry in the database
     *
     * @param productApi with the values to be created
     * @return ProductApi
     */
    ProductApi create(ProductApi productApi);

    /**
     * Retrieves one product from the database
     *
     * @param id identifying the product searched
     * @return ProductApi
     */
    ProductApi get(Long id);

    /**
     * Retrieves a all the product in the database
     *
     * @return List<ProductApi>
     */
    List<ProductApi> all();

    /**
     * Updates an existing product in the database
     *
     * @param id         the id of the product to be updated
     * @param productApi the new product information
     * @return ProductApi
     */
    ProductApi update(Long id, ProductApi productApi);

    /**
     * Removes a product from the database
     *
     * @param id identifying the product to remove
     */
    void remove(Long id);
}
