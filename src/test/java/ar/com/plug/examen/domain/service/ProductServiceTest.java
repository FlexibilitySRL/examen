/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.domain.model.Product;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msulbara
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductController productController;
    
    @Test
    @Transactional
    public void create() {
        Product p = new Product();
        p.setName("Product1");
        p.setDescription("this is a prodcut...");
        Product saved = productController.createProduct(p).getBody();

        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    @Transactional
    public void delete() {
        Product p = new Product();
        p.setId(2L);
        p.setName("Product2");
        p.setDescription("this is a prodcut...");

        productController.createProduct(p).getStatusCode();
        HttpStatus code = productController.deleteProduct(2L).getStatusCode();
        assertEquals(HttpStatus.OK, code);
    }
    
    @Test
    @Transactional
    public void update() {
        Product p = new Product();
        p.setId(4L);
        p.setName("Product4");
        p.setDescription("this is a prodcut...");
        
        Product savedproduct = productController.createProduct(p).getBody();
        savedproduct.setName("Newest Product");
        
        Product updatedProduct = productController.updateProduct(savedproduct).getBody();
        
        assertEquals("Newest Product", updatedProduct.getName());
    }

}
