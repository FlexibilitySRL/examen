package ar.com.flexibility.examen.domain.repo;

import ar.com.flexibility.examen.domain.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTest {

    @Autowired
    CustomerRepository repo;
    
    @Test
    public void saveTest() {
        Customer customer1 = new Customer();
        customer1.setName("Test maskname");
        Customer customer2 = repo.save(customer1);
        Assert.assertEquals(customer1.getName(), customer2.getName());
        Assert.assertNotNull("Customer should not be null", customer2.getId());
    }
    
    @Test
    public void queryTest() {
        Customer customer = new Customer();
        final String targetName = "Target Name Query Test";
        customer.setName(targetName);
        repo.save(customer);
        
        customer = new Customer();
        customer.setName(targetName);
        repo.save(customer);
        
        Assert.assertEquals(2, repo.findByMaskname(targetName).size());
    }
}
