package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ProductDTO;

import java.util.List;


/**
 * Service to save, update, delete and get products
 */
public interface ProductService
{
    /**
     * Save a product by the given productDTO
     *
     * @param productDTO
     * @return ProductDTO
     */
    ProductDTO save(ProductDTO productDTO);

    /**
     * Get all products
     * @return List<ProductDTO>
     */
    List<ProductDTO> getAllProducts();

    /**
     * Get Product by Id
     *
     * @param id
     * @return ProductDTO
     */
    ProductDTO getProductById(Long id);

    /**
     * Update a product
     *
     * @param productDTO
     * @return ProductDTO
     */
    ProductDTO update(ProductDTO productDTO);

    /**
     * Delete a product by Id
     *
     * @param id
     */
    void delete(Long id);

    /**
     * Get Product by Id and valid if exist in stock
     *
     * @param id
     * @param quantity
     * @return ProductDTO
     */
    ProductDTO getProductByIdInStock(Long id, Long quantity);

}
