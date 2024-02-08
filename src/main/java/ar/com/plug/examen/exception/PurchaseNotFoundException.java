package ar.com.plug.examen.exception;

import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Exception thrown when a purchase with a specified ID is not found.
 */
@NoArgsConstructor
public class PurchaseNotFoundException extends RuntimeException {

    /**
     * Constructs a new PurchaseNotFoundException with the specified purchase ID.
     *
     * @param purchaseId The unique identifier of the purchase causing the exception.
     */
    public PurchaseNotFoundException(UUID purchaseId) {
        super("Purchase not found with ID: " + purchaseId);
    }
}
