package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;
    private Product product;

    @Before
    public void initializer() {
        product = new Product("Coca", BigDecimal.valueOf(21.01));
    }

    @Test
    public void addProduct_validProduct_returnsProduct() {
        Product returnedProduct = productService.addProduct(product);

        assertEquals(product.getName(), returnedProduct.getName());
        assertEquals(product.getPrice(), returnedProduct.getPrice());
    }

    @Test
    public void updateProduct_validProduct_returnsUpdatedProduct() {
        productService.addProduct(product);

        product.setName("Fanta");
        product.setPrice(new BigDecimal(0.99));
        Product updatedProduct = productService.updateProduct(product);

        assertEquals("Fanta", updatedProduct.getName());
        assertEquals(new BigDecimal(0.99), updatedProduct.getPrice());
    }

    @Test
    public void findById_withExistingId_returnsSearchedProduct() {
        Product savedProduct = productService.addProduct(product);

        Product searchedProduct = productService.findById(savedProduct.getId());

        assertEquals(savedProduct.getId(), searchedProduct.getId());
        assertEquals(savedProduct.getName(), searchedProduct.getName());
        assertEquals(savedProduct.getPrice(), searchedProduct.getPrice());
    }

    @Test
    public void deleteProduct_withExistingProduct() {
        Product savedProduct = productService.addProduct(this.product);

        System.out.println("ProductServiceImplTest.deleteProduct_withExistingProduct");
        System.out.println("savedProduct.getPrice() = " + savedProduct.getPrice());

        productService.deleteProduct(savedProduct);

        assertNull(productService.findById(savedProduct.getId()));
    }
}
