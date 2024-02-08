package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.app.DTO.PurchaseDTO;

import java.util.List;
import java.util.UUID;

public interface PurchaseService {

    PurchaseDTO updatePurchase(UUID purchaseId, PurchaseDTO purchaseDTO);

    /**
     * Retrieves a purchase by its unique identifier.
     *
     * @param purchaseId The unique identifier of the purchase.
     * @return Optional containing the purchase details, or empty if not found.
     */
    PurchaseDTO getPurchaseById(UUID purchaseId);

    /**
     * @param id
     * @param vendorId
     * @return
     */
    PurchaseDTO approvePurchase(UUID id, UUID vendorId);

    List<PurchaseDTO> getPurchasesByCriteria(UUID customerId, UUID vendorId);
}

