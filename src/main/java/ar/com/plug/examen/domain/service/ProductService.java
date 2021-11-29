package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.dto.ProductDto;
import ar.com.plug.examen.app.dto.ProductStockDto;
import ar.com.plug.examen.domain.model.Product;
import java.util.List;


public interface ProductService {

    /**
     * List all products
     *
     * @return List<ProductApi>
     */
    List<ProductDto> findAll();

    /**
     * Find product by id
     *
     * @param id
     * @return ProductApi
     */
    ProductDto findById(Long id);

    /**
     * Save a product
     *
     * @param productApi
     * @return ProductApi
     */
    ProductDto save(ProductDto productApi);

    /**
     * Update a product
     *
     * @param productApi
     * @return ProductApi
     */
    ProductDto update(ProductDto productApi);

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
    List<Product> getProductsWithStock(List<ProductStockDto> listProducts);
}
