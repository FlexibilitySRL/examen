package ar.com.plug.examen.exception;

import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Exception thrown when a product with a specified ID is not found.
 */
@NoArgsConstructor
public class ProductNotFoundException extends RuntimeException {
    /**
     * Constructs a new ProductNotFoundException with the specified cart ID.
     *
     * @param productId
     */
    public ProductNotFoundException(UUID productId) {
        super("Product not found with ID: " + productId);
    }
}
