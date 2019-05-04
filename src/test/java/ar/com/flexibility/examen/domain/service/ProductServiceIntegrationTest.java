package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.exception.GenericProductException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceIntegrationTest {


    @Autowired
    private ProductServiceImpl productService;


    private static long PRODUCTS_COUNT;
    private static long PRODUCT_ID_EXIST_IN_DB;
    private static long PRODUCT_ID_NOT_EXIST_IN_DB;

    @Before
    public void setUp(){
        productService.deleteAll();

        Product product = new Product();
        product.setDescription("first product");
        product.setPrice(new BigDecimal(2.50));

        productService.add(product);

        List<Product> productList = productService.findAll();

        PRODUCTS_COUNT = productList.size();
        PRODUCT_ID_EXIST_IN_DB = productList.get((int) PRODUCTS_COUNT-1).getId();
        PRODUCT_ID_NOT_EXIST_IN_DB = PRODUCT_ID_EXIST_IN_DB + 1;
    }

    @Test
    public void testFindAll(){
        //given

        //when
        List<Product> products = productService.findAll();

        //then
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(PRODUCTS_COUNT, products.size());
    }

    @Test
    public void testFindOne(){
        //given
        Product p1 = null;
        Product p2 = null;

        //when
        try {
            p1 = productService.findOne(PRODUCT_ID_EXIST_IN_DB);
            p2 = productService.findOne(PRODUCT_ID_NOT_EXIST_IN_DB);
        } catch (NotFoundException e) {
        }

        //then
        assertNotNull(p1);
        assertEquals(PRODUCT_ID_EXIST_IN_DB, (long) p1.getId());
        assertNull(p2);
    }

    @Test
    public void testAdd(){
        //given
        Product productToAdd = new Product();
        productToAdd.setDescription("prueba add");
        productToAdd.setPrice(new BigDecimal(100.00));

        //when
        Product productAdded = productService.add(productToAdd);

        //then
        assertEquals(PRODUCT_ID_EXIST_IN_DB +1, (long) productAdded.getId());
        assertEquals(productToAdd.getDescription(), productAdded.getDescription());
        assertEquals(productToAdd.getPrice(), productAdded.getPrice());
    }

    @Test
    public void testUpdateOk(){
        //given
        Product productOriginal = null;
        try {
            //when
            productOriginal = productService.findOne(PRODUCT_ID_EXIST_IN_DB);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        //then
        assertNotNull(productOriginal);

        //given
        Product productToUse = new Product();
        productToUse.setId(PRODUCT_ID_EXIST_IN_DB);
        productToUse.setDescription("prueba update");

        Product productUpdated = null;
        try {
            //when
            productUpdated = productService.update(productToUse);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (GenericProductException e) {
            e.printStackTrace();
        }

        //then
        assertNotNull(productUpdated);
        assertEquals(productUpdated, productToUse);
        assertNotEquals(productUpdated, productOriginal);
    }

    @Test
    public void testUpdateNotFound(){
        //given
        Product productToUse = new Product();
        productToUse.setId(PRODUCT_ID_NOT_EXIST_IN_DB);

        Product productUpdated = new Product();
        try {
            //when
            productUpdated = productService.update(productToUse);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (GenericProductException e) {
            e.printStackTrace();
        }

        //then
        assertNull(productUpdated.getId());
    }

    @Test
    public void testUpdateNotChanges(){
        //given
        Product productOriginal = null;
        try {
            //when
            productOriginal = productService.findOne(PRODUCT_ID_EXIST_IN_DB);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        //then
        assertNotNull(productOriginal);

        //given
        Product productToUse = new Product();
        productToUse.setId(productOriginal.getId());
        productToUse.setDescription(productOriginal.getDescription());
        productToUse.setPrice(productOriginal.getPrice());

        //when
        Product productUpdated = null;
        try {
            productUpdated = productService.update(productToUse);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (GenericProductException e) {
            e.printStackTrace();
        }

        //then
        assertNull(productUpdated);
        assertEquals(productToUse, productOriginal);
    }

    @Test
    public void testDeleteOk(){

        Product product = null;
        try {
            //given
            assertNotNull(productService.findOne(PRODUCT_ID_EXIST_IN_DB));
            //when
            productService.delete(PRODUCT_ID_EXIST_IN_DB);
            product = productService.findOne(PRODUCT_ID_EXIST_IN_DB);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        //then
        assertNull(product);
    }

    @Test
    public void testDeleteNotFound(){
        //given
        Product product = null;
        try {
            //when
            productService.delete(PRODUCT_ID_NOT_EXIST_IN_DB);
            product = productService.findOne(PRODUCT_ID_NOT_EXIST_IN_DB);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        //then
        assertNull(product);
    }


}
