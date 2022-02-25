package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.PurchaseDTO;

import java.util.List;

public interface PurchaseService {
    void createPurchase(PurchaseDTO purchaseDTO);

    List<PurchaseDTO> listPurchase();

    void approvePurchase(long idPurchase);
}
