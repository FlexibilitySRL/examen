package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.repository.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.domain.service.exception.PurchaseOrderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;




    @Override
    public PurchaseOrder getPurchaseOrder(Long id) throws PurchaseOrderNotFound {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(id);

        if(purchaseOrder == null)
            throw new PurchaseOrderNotFound();

        return purchaseOrder;
    }


    @Override
    public PurchaseOrder requestPurchase(Client client, Product product, int amount) {

        PurchaseOrder purchaseOrder = new PurchaseOrder(client, product, amount);

        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        return purchaseOrder;
    }


    @Override
    public PurchaseOrder aprovePurchase(PurchaseOrder purchaseOrder) throws PurchaseOrderNotFound {

        PurchaseOrder purchaseOrder1 = getPurchaseOrder(purchaseOrder.getId());

        purchaseOrder1.setStatus(PurchaseOrder.Status.ACCEPTED);

        return purchaseOrderRepository.save(purchaseOrder1);
    }


    @Override
    public PurchaseOrder revokePurchase(PurchaseOrder purchaseOrder) throws PurchaseOrderNotFound {

        PurchaseOrder purchaseOrder1 = getPurchaseOrder(purchaseOrder.getId());

        purchaseOrder1.setStatus(PurchaseOrder.Status.REVOKED);

        return purchaseOrderRepository.save(purchaseOrder1);
    }

}
