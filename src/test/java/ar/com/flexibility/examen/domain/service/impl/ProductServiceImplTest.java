package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProductService productService;
    private Product product;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setName("Camera Nikon T50");
        product.setDescription("Camera Nikon Series T50");

        product = entityManager.merge(product);
    }

    @Test
    public void create() {
        ProductApi productApi = new ProductApi();
        productApi.setName("Jessie");

        ProductApi created = productService.create(productApi);
        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test(expected = BadRequestException.class)
    public void create_throw() {
        ProductApi productApi = new ProductApi();
        productApi.setName(StringUtils.EMPTY);

        productService.create(productApi);
    }

    @Test
    public void retrieve() {
        ProductApi productApi = productService.retrieve(product.getId());
        assertNotNull(productApi);
        assertEquals(product.getId(), productApi.getId());
    }

    @Test(expected = NotFoundException.class)
    public void retrieve_wrongCode() {
        productService.retrieve(product.getId() * -1);
    }

    @Test
    public void list() {
        List<ProductApi> clientList = productService.list();
        assertFalse(clientList.isEmpty());
    }

    @Test(expected = NotFoundException.class)
    public void remove() {
        Product product = new Product();
        product.setName("Jose");
        product = entityManager.merge(product);

        productService.remove(product.getId());

        productService.retrieve(product.getId());
    }

    @Test
    public void update() {
        ProductApi productApi = new ProductApi();
        productApi.setId(product.getId());
        productApi.setName("new name");

        ProductApi updated = productService.update(product.getId(), productApi);
        assertNotNull(updated);
        assertEquals(productApi.getId(), updated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void update_wrongCode() {
        ProductApi productApi = new ProductApi();
        productApi.setId(productApi.getId());
        productApi.setName("new name");

        productService.update(product.getId() * -1, productApi);
    }
}