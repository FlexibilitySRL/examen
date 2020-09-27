package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.exception.ProductAlreadyExistsException;
import ar.com.plug.examen.domain.service.exception.ProductNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplCrudTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void testCreateProduct() throws ProductAlreadyExistsException {
        Product product = getProductUnderTest();
        product = productService.createProduct(product);

        assertNotNull(product.getCod());
    }

    @Test
    public void testGetProduct__whenExists() throws ProductAlreadyExistsException, ProductNotFoundException {
        Product product = getProductUnderTest();
        product = productService.createProduct(product);
        assertEquals(product, productService.getProduct(product.getCod()));
    }

    @Test(expected = ProductNotFoundException.class)
    public void testGetProduct__whenDoesntExists() throws ProductNotFoundException {
        productService.getProduct("imaginary_cod");
    }

    @Test
    public void testDeleteProduct() throws ProductAlreadyExistsException, ProductNotFoundException {
        Product product = getProductUnderTest();
        product = productService.createProduct(product);
        product = productService.getProduct(product.getCod());
        productService.deleteProduct(product.getCod());
    }

    @Test(expected = ProductNotFoundException.class)
    public void testDeleteProduct__whenProductDoesntExists() throws ProductAlreadyExistsException, ProductNotFoundException {
        Product product = getProductUnderTest();
        product.setCod("test_code");
        productService.deleteProduct(product.getCod());
    }

    @Test
    public void testUpdateProduct() throws ProductAlreadyExistsException, ProductNotFoundException {
        Product product = getProductUnderTest();
        product = productService.createProduct(product);

        product.setName("Name Changed");
        product = productService.updateProduct(product);

        assertEquals("Name Changed", product.getName());
    }

    @Test(expected = ProductNotFoundException.class)
    public void testUpdateProduct__whenProductDoesntExists() throws ProductNotFoundException {
        Product product = getProductUnderTest();
        product.setCod("imaginary_cod_test");
        productService.updateProduct(product);
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
