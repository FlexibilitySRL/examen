package ar.com.plug.examen.exception;

import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Exception thrown when a cart with a specified ID is not found.
 */
@NoArgsConstructor
public class CartNotFoundException extends RuntimeException {
    /**
     * Constructs a new CartNotFoundException with the specified cart ID.
     *
     * @param cartId
     */
    public CartNotFoundException(UUID cartId) {
        super("Cart not found with ID: " + cartId);
    }
}
