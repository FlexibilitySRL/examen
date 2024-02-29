package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testCrud(){
        Product product = new Product();
        product.setValue(BigDecimal.valueOf(10));
        product.setName("Test_1");
        productService.saveOrUpdate(product);
        List<Product> productList1 = this.productService.getAll();
        Assert.assertEquals(productList1.size(),1);

        Long id = productList1.get(0).getId();

        Product oldProduct = this.productService.getById(id);
        Assert.assertEquals(oldProduct.getName(),"Test_1");

        this.productService.delete(id);
        List<Product> productList2 = this.productService.getAll();
        Assert.assertEquals(productList2.size(),0);
    }


}
