package ar.com.plug.examen.api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class ProductControllerTest {

	@Autowired
	private ProductController productController;

	@Test
	public void testListAll() {
		List<ProductApi> all = productController.listProducts().getBody();
		assertFalse(all.isEmpty());
	}

	@Test
	public void testFindByName() {
		List<ProductApi> all = productController.findByName("Product A").getBody();
		assertFalse(all.isEmpty());
		assertEquals(1, all.size());
	}

	@Test
	@Transactional
	public void testSave() throws BadRequestException {
		ProductApi newProduct = new ProductApi("Product Z", 5.0D);
		ProductApi saved = productController.save(newProduct).getBody();
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assertEquals(newProduct.getName(), saved.getName());
	}

	@Test
	@Transactional
	public void testDeleteById() throws NotFoundException {
		HttpStatus code = productController.deleteById(1L).getStatusCode();
		assertEquals(HttpStatus.NO_CONTENT, code);
	}

	@Test
	@Transactional
	public void testUpdate() throws NotFoundException, BadRequestException  {
		ProductApi oldProduct = productController.findById(2L).getBody();
		ProductApi updated = new ProductApi(oldProduct.getId(), "Product Dummy", 0D);
		updated = productController.update(updated).getBody();
		assertNotNull(updated);
		assertNotNull(updated.getId());
		assertEquals(oldProduct.getId(), updated.getId());
		assertNotEquals(oldProduct.getName(), updated.getName());
	}
}