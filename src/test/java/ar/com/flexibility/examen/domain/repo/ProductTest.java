package ar.com.flexibility.examen.domain.repo;

import ar.com.flexibility.examen.domain.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductTest {
    
    @Autowired
    ProductRepository repo;
    
    @Test
    public void saveTest() {
        Product product = new Product();
        product.setName("Product 1");
        
        Product productSaved = repo.save(product);
        Assert.assertEquals("Product 1", productSaved.getName());
        Assert.assertNotNull(productSaved.getId());
    }
    
    @Test
    public void queryTest() {
        Product product = new Product();
        final String targetName = "Target Product Name Query Test";
        product.setName(targetName);
        repo.save(product);
        
        product = new Product();
        product.setName(targetName);
        repo.save(product);
        
        Assert.assertEquals(2, repo.findByName(targetName).size());
    }
}
