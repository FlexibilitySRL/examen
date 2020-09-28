package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.PurchaseOrder;
import ar.com.plug.examen.domain.repository.PurchaseOrderRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.PurchaseService;
import ar.com.plug.examen.domain.service.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.service.exception.PurchaseOrderEvaluationTransactionException;
import ar.com.plug.examen.domain.service.exception.PurchaseOrderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;


    /**
     * Place a new Purchase Order / Add to cart operation
     * Not a transactional operation so the cart operations of all users could be faster assuming that
     * the client is not sure about buying it -also other business nor functional considerations apply-
     * Purchase Order must be approved to verify if the requested amount of products is valid according
     * the inventory in the database
     * @param client
     * @param product
     * @param amount
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public PurchaseOrder requestPurchase(Client client, Product product, int amount)
            throws ProductNotFoundException {
        PurchaseOrder purchaseOrder = new PurchaseOrder(client, product, amount);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        return purchaseOrder;
    }

    /**
     * Get a requested Purchase Order -approved or not-
     * @param id
     * @return
     * @throws PurchaseOrderNotFound
     */
    @Override
    public PurchaseOrder getPurchaseOrder(Long id) throws PurchaseOrderNotFound {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);

        if(!purchaseOrder.isPresent())
            throw new PurchaseOrderNotFound();

        return purchaseOrder.get();
    }


    /**
     * Approve a Purchase Order and proceed to discount the amount of product from the
     * inventory
     * Ideal when the client already paid for the products and are not available to the
     * public anymore
     * @param purchaseOrderTmp
     * @return
     * @throws PurchaseOrderNotFound
     * @throws PurchaseOrderEvaluationTransactionException
     * @throws ProductNotFoundException
     */
    @Override
    @Transactional(rollbackFor = {PurchaseOrderEvaluationTransactionException.class, RuntimeException.class})
    public PurchaseOrder approvePurchase(PurchaseOrder purchaseOrderTmp) throws PurchaseOrderNotFound,
            PurchaseOrderEvaluationTransactionException,
            ProductNotFoundException {
        // Consult the Purchase Order
        PurchaseOrder purchaseOrder = getPurchaseOrder(purchaseOrderTmp.getId());

        // Verify Product stock
        Product product = productService.getProduct(purchaseOrderTmp.getProduct().getCod());
        if(purchaseOrder.getAmount() > product.getStock())
            throw new PurchaseOrderEvaluationTransactionException("Product amount in stock not enough");
        // Set the new Product stock amount of items
        product.setStock(product.getStock() - purchaseOrder.getAmount());
        productService.updateProduct(product);

        // Approve the Purchase Order
        purchaseOrder.setStatus(PurchaseOrder.Status.ACCEPTED);

        return purchaseOrderRepository.save(purchaseOrder);
    }


    @Override
    public PurchaseOrder revokePurchase(PurchaseOrder purchaseOrderTmp) throws PurchaseOrderNotFound {
        PurchaseOrder purchaseOrder = getPurchaseOrder(purchaseOrderTmp.getId());
        purchaseOrder.setStatus(PurchaseOrder.Status.REVOKED);

        return purchaseOrderRepository.save(purchaseOrder);
    }

}
