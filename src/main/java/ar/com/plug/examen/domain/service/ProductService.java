package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.app.DTO.ProductDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing Product operations.
 */
public interface ProductService {

    /**
     * Adds a new product.
     *
     * @param productDTO The product details.
     */
    ProductDTO addProduct(ProductDTO productDTO);

    /**
     * Updates an existing product.
     *
     * @param productId  The unique identifier of the product.
     * @param productDTO The updated product details.
     */
    ProductDTO updateProduct(UUID productId, ProductDTO productDTO);

    /**
     * Deletes a product.
     *
     * @param productId The unique identifier of the product.
     */
    void deleteProduct(UUID productId);

    /**
     * Retrieves a list of all products.
     *
     * @return List of all products.
     */
    List<ProductDTO> getAllProducts();

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @return Optional containing the product details, or empty if not found.
     */
    Optional<ProductDTO> getProductById(UUID productId);
}
