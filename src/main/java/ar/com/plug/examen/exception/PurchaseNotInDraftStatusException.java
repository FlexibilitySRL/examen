package ar.com.plug.examen.exception;

import java.util.UUID;

/**
 * Exception thrown when attempting to approve a purchase that is not in 'DRAFT' status.
 */
public class PurchaseNotInDraftStatusException extends RuntimeException {

    /**
     * Constructs a new PurchaseNotInDraftStatusException with the specified purchase ID.
     *
     * @param purchaseId The unique identifier of the purchase causing the exception.
     */
    public PurchaseNotInDraftStatusException(UUID purchaseId) {
        super("Cannot approve purchase with ID " + purchaseId + " because it is not in 'DRAFT' status");
    }
}
