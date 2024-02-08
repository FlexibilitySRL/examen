package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.app.DTO.CartDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {
    /**
     * Adds a new cart.
     *
     * @param customerId The cart details.
     */
    CartDTO addCart(UUID customerId);

    /**
     * Adds a new product in cart
     *
     * @param id
     * @param productId
     * @param quantity
     * @return
     */
    CartDTO addProduct(UUID id, UUID productId, Integer quantity);

    /**
     * Updates a product in cart
     *
     * @param id
     * @param productId
     * @param quantity
     * @return
     */
    CartDTO updateProduct(UUID id, UUID productId, Integer quantity);

    /**
     * delete a product in cart
     *
     * @param id
     * @param productId
     * @return
     */
    CartDTO deleteProduct(UUID id, UUID productId);

    /**
     * Add a purchase in cart.
     *
     * @param id
     * @return
     */
    CartDTO completeCart(UUID id);

    /**
     * Get all
     *
     * @return
     */
    List<CartDTO> getAllCarts();

    /**
     * @param cartId
     * @return
     */
    Optional<CartDTO> getCartById(UUID cartId);
}

