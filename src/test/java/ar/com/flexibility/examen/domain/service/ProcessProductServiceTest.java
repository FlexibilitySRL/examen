package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.service.impl.ProcessProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessProductServiceTest {

	@Autowired
	private ProcessProductServiceImpl productService;
	
	@Test
	public void shouldCreateAProduct() {
		ProductApi productApi = new ProductApi();
		productApi.setName("Board");
		productApi.setType("Hardware");

		productApi = productService.create(productApi);

		assertNotNull(productApi.getId());
		assertEquals(productApi.getName(), "Board");
	}

	@Test
	public void shouldUpdateAProduct() {
		ProductApi productApi = new ProductApi();
		productApi.setName("RAM");
		productApi.setType("Hardware");

		productApi = productService.create(productApi);
		
		productApi = productService.searchByName("RAM");
		productApi.setName("RAM upgrade");

		productApi = productService.update(productApi.getId(), productApi);

		assertNotNull(productApi);
		assertEquals(productApi.getName(), "RAM upgrade");
	}

	@Test
	public void shouldDeleteAProduct() {
		ProductApi productApi;
		productApi = productService.searchByName("RAM upgrade");
		productService.delete(productApi);
		productApi = productService.searchByName("RAM upgrade");
		assertNull(productApi);
	}
}
