package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.PurchaseOrder;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.PurchaseService;
import ar.com.plug.examen.domain.service.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.service.exception.PurchaseOrderEvaluationTransactionException;
import ar.com.plug.examen.domain.service.exception.PurchaseOrderNotFound;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseServiceImplTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @Test
    public void testRequestPurchaseOrder() throws ProductNotFoundException {
        Client client = clientService.createClient(getClientUnderTest());
        Product product = productService.createProduct(getProductUnderTest());

        PurchaseOrder purchaseOrder = purchaseService.requestPurchase(client, product, 10);

        assertNotNull(purchaseOrder);
        assertNotNull(purchaseOrder.getId());
    }

    @Test
    public void testGetPurchaseOrder() throws ProductNotFoundException, PurchaseOrderNotFound {
        Client client = clientService.createClient(getClientUnderTest());
        Product product = productService.createProduct(getProductUnderTest());

        PurchaseOrder purchaseOrder = purchaseService.requestPurchase(client, product, 10);
        PurchaseOrder seekedPurchaseOrder = purchaseService.getPurchaseOrder(purchaseOrder.getId());

        assertEquals(purchaseOrder, seekedPurchaseOrder);
    }

    @Test(expected = PurchaseOrderNotFound.class)
    public void testGetPurchaseOrder__whenPurchaseOrderIsNotFound() throws PurchaseOrderNotFound {
        purchaseService.getPurchaseOrder(123L);
    }

    @Test
    public void testApprovePurchaseOrder() throws ProductNotFoundException, PurchaseOrderNotFound, PurchaseOrderEvaluationTransactionException {
        Client client = clientService.createClient(getClientUnderTest());
        Product product = productService.createProduct(getProductUnderTest());
        PurchaseOrder purchaseOrder = purchaseService.requestPurchase(client, product, 10);
        int inventoryAmountOfItems = product.getStock();

        purchaseService.approvePurchase(purchaseOrder);
        purchaseOrder = purchaseService.getPurchaseOrder(purchaseOrder.getId());

        product = productService.getProduct(product.getCod());

        assertEquals(PurchaseOrder.Status.ACCEPTED, purchaseOrder.getStatus());
        assertEquals(inventoryAmountOfItems - 10, product.getStock().intValue());
    }

    @Test(expected = PurchaseOrderEvaluationTransactionException.class)
    public void testApprovePurchaseOrder__whenAmountOfRequestedProductsAreGreaterThanAvailableInStock() throws ProductNotFoundException,
            PurchaseOrderNotFound, PurchaseOrderEvaluationTransactionException {
        Client client = clientService.createClient(getClientUnderTest());
        Product product = productService.createProduct(getProductUnderTest());
        PurchaseOrder purchaseOrder = purchaseService.requestPurchase(client, product, 200);

        purchaseService.approvePurchase(purchaseOrder);
    }


    @Test
    public void testRevokePurchaseOrder() throws ProductNotFoundException, PurchaseOrderNotFound, PurchaseOrderEvaluationTransactionException {
        Client client = clientService.createClient(getClientUnderTest());
        Product product = productService.createProduct(getProductUnderTest());
        PurchaseOrder purchaseOrder = purchaseService.requestPurchase(client, product, 10);

        purchaseService.revokePurchase(purchaseOrder);
        purchaseOrder = purchaseService.getPurchaseOrder(purchaseOrder.getId());

        assertEquals(PurchaseOrder.Status.REVOKED, purchaseOrder.getStatus());
    }

    private Client getClientUnderTest() {
        Client client = new Client();
        client.setFirstName("Jack");
        client.setLastName("Sparrow");
        client.setAge("34");

        return client;
    }

    private Product getProductUnderTest() {
        Product product = new Product();
        product.setName("PS5 Bundle");
        product.setDescription("2020 PS5 bundle with two controllers and BF6");
        product.setStock(100);
        product.setCod("cod_ps5_bundle1");
        product.setPrice(1500D);

        return product;
    }

}
