package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductServiceTest {
    
    @Autowired
    ProductService svc;
    
    @Test
    public void createTest() {
        Product product = new Product();
        product.setName("Product Name");
        Product productRes = svc.create(product);
        Assert.assertEquals(product.getName(), productRes.getName());
    }
    
    @Test
    public void readTest() {
        String productName = "ProductName 1";
        Product product = new Product();
        product.setName(productName);
        product = svc.create(product);

        Product productRead = svc.read(product.getId());
        Assert.assertEquals(productName, productRead.getName());
    }

    @Test
    public void updateTest() {
        String maskname = "ProductName 2";
        Product product = new Product();
        product.setName(maskname);
        product = svc.create(product);

        String productName = "Updated Product Name";
        Long id = product.getId();
        Product updProduct = new Product();
        updProduct.setId(id);
        updProduct.setName(productName);
        updProduct = svc.update(updProduct);
        Assert.assertEquals("A new object were created", product.getId(), updProduct.getId());
        Assert.assertEquals("Product Name wasn't updated", productName, svc.read(id).getName());
    }

    @Test
    public void deleteTest() {
        String maskname = "maskname 3";
        Product product = new Product();
        product.setName(maskname);
        product = svc.create(product);

        svc.delete(product.getId());
        Assert.assertNull(svc.read(product.getId()));
    }
    
}
