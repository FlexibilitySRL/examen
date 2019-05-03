package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.exception.GenericProductException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceTest {

    @Autowired
    ProductServiceImpl productService;

    private static long PRODUCTS_COUNT;
    private static long PRODUCT_ID_EXIST_IN_DB;
    private static long PRODUCT_ID_NOT_EXIST_IN_DB;

    @Before
    public void setUp(){
        productService.deleteAll();

        Product product = new Product();
        product.setDescription("first product");
        product.setPrice(new BigDecimal(2.50));

        try {
            product = productService.add(product);
        } catch (GenericProductException e) {
            assertNull(product);
            e.printStackTrace();
        }

        List<Product> productList = productService.findAll();

        PRODUCTS_COUNT = productList.size();
        PRODUCT_ID_EXIST_IN_DB = productList.get((int) PRODUCTS_COUNT-1).getId();
        PRODUCT_ID_NOT_EXIST_IN_DB = PRODUCT_ID_EXIST_IN_DB + 1;
    }

    @Test
    public void testFindAll(){
        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(PRODUCTS_COUNT, products.size());
    }

    @Test
    public void testFindOne(){

        Product p1 = null;
        Product p2 = null;

        try {
            p1 = productService.findOne(PRODUCT_ID_EXIST_IN_DB);
            p2 = productService.findOne(PRODUCT_ID_NOT_EXIST_IN_DB);
        } catch (NotFoundException e) {
        }

        assertNotNull(p1);
        assertEquals(PRODUCT_ID_EXIST_IN_DB, (long) p1.getId());

        assertNull(p2);
    }

    @Test
    public void testExists(){
        assertTrue(productService.exists(PRODUCT_ID_EXIST_IN_DB));
        assertFalse(productService.exists(PRODUCT_ID_NOT_EXIST_IN_DB));
    }

    @Test
    public void testAdd(){
        Product productToAdd = new Product();
        productToAdd.setDescription("prueba add");
        productToAdd.setPrice(new BigDecimal(100.00));

        Product productAdded = null;
        try {
            productAdded = productService.add(productToAdd);
        } catch (GenericProductException e) {
            e.printStackTrace();
        }

        assertEquals(PRODUCT_ID_EXIST_IN_DB +1, (long) productAdded.getId());
        assertEquals(productToAdd.getDescription(), productAdded.getDescription());
        assertEquals(productToAdd.getPrice(), productAdded.getPrice());
    }

    @Test
    public void testUpdateOk(){
        Product productOriginal = null;
        try {
            productOriginal = productService.findOne(PRODUCT_ID_EXIST_IN_DB);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(productOriginal);

        // Set ID exist in DB
        Product productToUse = new Product();
        productToUse.setId(PRODUCT_ID_EXIST_IN_DB);
        productToUse.setDescription("prueba update");

        Product productUpdated = null;
        try {
            productUpdated = productService.update(productToUse);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (GenericProductException e) {
            e.printStackTrace();
        }

        assertNotNull(productUpdated);
        assertEquals(productUpdated, productToUse);
        assertNotEquals(productUpdated, productOriginal);
    }

    @Test
    public void testUpdateNotFound(){
        // Set ID not exist in DB
        Product productToUse = new Product();
        productToUse.setId(PRODUCT_ID_NOT_EXIST_IN_DB);

        Product productUpdated = new Product();
        try {
            productUpdated = productService.update(productToUse);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (GenericProductException e) {
            e.printStackTrace();
        }
        assertNull(productUpdated.getId());
    }

    @Test
    public void testUpdate(){
        Product productOriginal = null;
        try {
            productOriginal = productService.findOne(PRODUCT_ID_EXIST_IN_DB);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(productOriginal);

        // Update without changes
        Product productToUse = new Product();
        productToUse.setId(productOriginal.getId());
        productToUse.setDescription(productOriginal.getDescription());
        productToUse.setPrice(productOriginal.getPrice());

        Product productUpdated = null;
        try {
            productUpdated = productService.update(productToUse);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (GenericProductException e) {
            e.printStackTrace();
        }

        assertNull(productUpdated);
        assertEquals(productToUse, productOriginal);
    }

    @Test
    public void testDelete(){
        assertTrue(productService.exists(PRODUCT_ID_EXIST_IN_DB));
        try {
            productService.delete(PRODUCT_ID_EXIST_IN_DB);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (GenericProductException e) {
            e.printStackTrace();
        }
        assertFalse(productService.exists(PRODUCT_ID_EXIST_IN_DB));

        assertFalse(productService.exists(PRODUCT_ID_NOT_EXIST_IN_DB));
        try {
            productService.delete(PRODUCT_ID_NOT_EXIST_IN_DB);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (GenericProductException e) {
            e.printStackTrace();
        }
        assertFalse(productService.exists(PRODUCT_ID_NOT_EXIST_IN_DB));
    }


}
