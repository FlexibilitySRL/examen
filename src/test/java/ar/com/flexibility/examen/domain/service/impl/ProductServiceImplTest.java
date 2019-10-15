package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.persistence.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class ProductServiceImplTest {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() throws Exception {

        Product product = Product.builder().description("desc").build();
        repository.save(product);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void update() {

        Product product = Product.builder().description("description").build();
        repository.save(product);

        Product product2 = Product.builder().description("descriptionUpdated").build();
        productService.update(product.getId(), product2);

        Product productUpdated = repository.getOne(product.getId());
        assertTrue("descriptionUpdated".equals(productUpdated.getDescription()));

    }

    @Test
    public void save() {

        Product product = Product.builder().description("description").build();
        productService.save(product);

        Product productSaved = repository.getOne(product.getId());
        assertNotNull(productSaved);
    }

    @Test
    public void delete() {

        Product product = Product.builder().description("description").build();
        repository.save(product);

        productService.delete(product.getId());

        Product productSaved = repository.findOne(product.getId());
        assertNull(productSaved);

    }

    @Test
    public void all() {
        assertTrue(productService.findAll().size() > 0);
    }
}