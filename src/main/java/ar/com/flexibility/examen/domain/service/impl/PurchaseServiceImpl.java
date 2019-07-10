package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.repository.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.domain.service.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.service.exception.PurchaseOrderEvaluationTransactionException;
import ar.com.flexibility.examen.domain.service.exception.PurchaseOrderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductService productService;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = PurchaseOrderEvaluationTransactionException.class)
    public PurchaseOrder aprovePurchase(PurchaseOrder purchaseOrderTmp) throws PurchaseOrderNotFound,
            PurchaseOrderEvaluationTransactionException,
            ProductNotFoundException {

        Product product = productService.getProductById(purchaseOrderTmp.getProduct().getId());
        PurchaseOrder purchaseOrder = getPurchaseOrder(purchaseOrderTmp.getId());

        if(purchaseOrder.getAmount() > product.getStock())
            throw new PurchaseOrderEvaluationTransactionException("Product amount not enough in stock");

        purchaseOrder.setStatus(PurchaseOrder.Status.ACCEPTED);

        return purchaseOrderRepository.save(purchaseOrder);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = PurchaseOrderEvaluationTransactionException.class)
    public PurchaseOrder revokePurchase(PurchaseOrder purchaseOrderTmp) throws PurchaseOrderNotFound,
            PurchaseOrderEvaluationTransactionException,
            ProductNotFoundException {

        Product product = productService.getProductById(purchaseOrderTmp.getProduct().getId());
        PurchaseOrder purchaseOrder = getPurchaseOrder(purchaseOrderTmp.getId());

        purchaseOrder.setStatus(PurchaseOrder.Status.REVOKED);

        return purchaseOrderRepository.save(purchaseOrder);
    }

}
