package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PurchaseServiceTest {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @Test
    public void shop() {
        Purchase purchase = purchaseService.createNewPurchase(this.createSeller().getId(), this.createCustomer().getId());

        Assert.assertNotNull("El id no deberia ser nulo", purchase.getId());

        purchaseService.addProductToPurchase(purchase.getId(), this.createProduct().getId());
        purchaseService.addProductToPurchase(purchase.getId(), this.createProduct().getId());
        purchaseService.addProductToPurchase(purchase.getId(), this.createProduct().getId());
        purchaseService.endPurchase(purchase.getId());

        Purchase purchase1 = purchaseService.find(purchase.getId());

        Assert.assertEquals("La cantidad de items deberia ser 3.", 3, purchase1.getItems().size());
        Assert.assertEquals("El estado deberia ser PENDING", PurchaseStatus.PENDING, purchase1.getStatus());
    }

    @Test
    public void shopAndApprove() {
        Purchase purchase = purchaseService.createNewPurchase(this.createSeller().getId(), this.createCustomer().getId());

        Assert.assertNotNull("El id no deberia ser nulo", purchase.getId());

        purchaseService.addProductToPurchase(purchase.getId(), this.createProduct().getId());
        purchaseService.endPurchase(purchase.getId());

        Purchase purchase1 = purchaseService.find(purchase.getId());

        Assert.assertEquals("La cantidad de items deberia ser 1.", 1, purchase1.getItems().size());
        Assert.assertEquals("El estado deberia ser PENDING", PurchaseStatus.PENDING, purchase1.getStatus());

        purchaseService.changeStatusToPurchase(purchase1.getId(), PurchaseStatus.APPROVED);

        Purchase purchase2 = purchaseService.find(purchase.getId());

        Assert.assertEquals("El estado deberia ser APPROVED", PurchaseStatus.APPROVED, purchase2.getStatus());
    }

    private Seller createSeller() {
        Seller seller = new Seller();
        seller.setFirstName("Juan");
        seller.setLastName("Vende");
        return sellerService.save(seller);
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Pedro");
        customer.setLastName("Compra");
        return customerService.save(customer);
    }

    private Product createProduct() {
        Product product = new Product();
        product.setName(RandomStringUtils.randomAlphabetic(10));
        product.setDescription(RandomStringUtils.randomAlphabetic(25));
        return productService.save(product);
    }

}
