package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.ProductStockApi;
import ar.com.plug.examen.domain.model.Product;
import java.util.List;


public interface ProductService {

    /**
     * List all products
     *
     * @return List<ProductApi>
     */
    List<ProductApi> findAll();

    /**
     * Find product by id
     *
     * @param id
     * @return ProductApi
     */
    ProductApi findById(Long id);

    /**
     * Save a product
     *
     * @param productApi
     * @return ProductApi
     */
    ProductApi save(ProductApi productApi);

    /**
     * Update a product
     *
     * @param productApi
     * @return ProductApi
     */
    ProductApi update(ProductApi productApi);

    /**
     * Delete a product by id
     *
     * @param id
     */
    void delete(Long id);

    /**
     * Return all products in stock
     *
     * @param listProducts
     * @return List<Product>
     */
    List<Product> getProductsWithStock(List<ProductStockApi> listProducts);
}
