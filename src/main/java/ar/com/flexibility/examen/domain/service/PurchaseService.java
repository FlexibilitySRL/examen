package ar.com.flexibility.examen.domain.service;


import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.service.exception.PurchaseOrderNotFound;


public interface PurchaseService {

    PurchaseOrder requestPurchase(Client client, Product product, int amount);
    PurchaseOrder aprovePurchase(PurchaseOrder purchaseOrder) throws PurchaseOrderNotFound;
    PurchaseOrder revokePurchase(PurchaseOrder purchaseOrder) throws PurchaseOrderNotFound;

    PurchaseOrder getPurchaseOrder(Long id) throws PurchaseOrderNotFound;
}
