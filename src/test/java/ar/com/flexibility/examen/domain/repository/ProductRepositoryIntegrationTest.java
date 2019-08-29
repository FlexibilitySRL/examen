package ar.com.flexibility.examen.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.domain.model.Product;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindOne_thenReturnProduct() {
    	Product product = new Product();
		product.setId(1L);
		product.setName("notebook");
		product.setDescription("Dell 16gb");
		product.setPrice(50000.00);
		product.setStock(10);
        
		entityManager.persist(product);
        entityManager.flush();
     
        // when
        Product found = productRepository.findOne(1L);
     
        // then
        assertEquals(found.getId(),product.getId());
        assertEquals(found.getName(),product.getName());
    }

}
