package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityNotFoundException;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.domain.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Autowired
	ProductService productService;
	
	@Rule
    public ExpectedException thrownException = ExpectedException.none();
	
	@Test
	public void createProduct() {

		Product product = new Product();
		product.setDescription("description");
		product.setName("name");
		product.setPrice(123.0);
		product.setStock(20);
		
		Product productSaved = productService.save(product);
		
		assertNotNull(productSaved);
		assertEquals(productSaved.getDescription(), product.getDescription());
		assertEquals(productSaved.getName(), product.getName());
		assertEquals(productSaved.getPrice(), product.getPrice());
		assertEquals(productSaved.getStock(), product.getStock());
	}
	
	@Test
	public void deleteProduct(){
		
		Product product = new Product();
		product.setDescription("descriptioon");
		product.setName("namee");
		product.setPrice(123.0);
		product.setStock(20);
		
		Product productSaved = productService.save(product);
		
		productService.deleteProduct(productSaved.getId());
		
		thrownException.expect(EntityNotFoundException.class);
		Assert.assertNull(productService.getProductById(productSaved.getId()));
	}

}
