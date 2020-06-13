package ar.com.flexibility.examen.domain.repo;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PurchaseRepositoryTest {
    @Autowired
    PurchaseRepository purchaseRepo;
    
    @Autowired
    CustomerRepository customerRepo;
    
    @Autowired
    ProductRepository productRepo;
    
    @Test
    public void test() {
        Product prod = new Product();
        prod.setName("Nice product");
        prod = productRepo.save(prod);
        
        Customer cust = new Customer();
        cust.setName("Good Customer");
        cust = customerRepo.save(cust);
        
        Purchase purchase = new Purchase();
        purchase.setCustomer(cust);
        purchase.setProduct(prod);
        purchase = purchaseRepo.save(purchase);
        
        Purchase purchaseRead = purchaseRepo.findOne(purchase.getId());
        
        Assert.assertEquals(purchase.getId(), purchaseRead.getId());
        Assert.assertEquals(prod.getId(), purchaseRead.getProduct().getId());
        Assert.assertEquals(cust.getId(), purchaseRead.getCustomer().getId());
    }
    
    @Test
    public void testFind() {
        Product prod = new Product();
        prod.setName("Query product");
        prod = productRepo.save(prod);
        
        Customer cust = new Customer();
        cust.setName("Query Customer");
        cust = customerRepo.save(cust);
        
        Purchase purchase = new Purchase();
        purchase.setCustomer(cust);
        purchase.setProduct(prod);
        purchase = purchaseRepo.save(purchase);
        
        Assert.assertEquals(1, purchaseRepo.findByCustomerAndProduct(cust, prod).size());
    }
}
