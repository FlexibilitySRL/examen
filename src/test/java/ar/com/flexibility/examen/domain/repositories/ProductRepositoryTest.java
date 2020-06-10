package ar.com.flexibility.examen.domain.repositories;

import ar.com.flexibility.examen.domain.model.Product;
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
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @Before
    public void setup() {
        product = new Product();
        product.setName("Camera Nikon T50");
        product.setDescription("Camera Nikon series T50");

        product = entityManager.merge(product);
    }

    @Test
    public void create() {
        product = productRepository.save(product);
        assertNotNull(product.getId());
    }

    @Test
    public void update() {
        Product productToUpdate = new Product();
        productToUpdate.setName("Camera Nikon T500");

        Product updated = productRepository.save(productToUpdate);
        assertNotNull(updated);
        assertEquals(productToUpdate.getId(), updated.getId());
    }

    @Test
    public void retrieve() {
        Product retrieved = productRepository.findById(product.getId()).orElse(null);
        assertNotNull(retrieved);
        assertNotNull(retrieved.getId());
        assertEquals(product.getId(), retrieved.getId());
    }

    @Test
    public void list() {
        List<Product> lists = productRepository.findAll();
        assertNotNull(lists);
        assertFalse(lists.isEmpty());
    }

    @Test
    public void delete() {
        Product product = new Product();
        product.setName("Camera Cannon NS");
        product.setDescription("Another camera");
        product = entityManager.merge(product);

        productRepository.delete(product.getId());

        assertFalse(productRepository.findById(product.getId()).isPresent());
    }

}