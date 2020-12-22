package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.domain.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;

	@Test
	public void testNewProduct() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Product product = new Product(44, "Producto 1", "Descripcion producto 1", (float) 1000.1, null);
		ResponseEntity<?> responseEntity = productController.newProduct(product);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void testOne() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		long id = 1;
		ResponseEntity<?> responseEntity = productController.one(id);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testAll() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		ResponseEntity<?> responseEntity = productController.all();
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdate() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Product product = new Product(44, "Producto 1", "Descripcion producto 1", (float) 1000.1, null);
		ResponseEntity<?> responseEntity = productController.update(product);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testDelete() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		long id = 1;
		ResponseEntity<?> responseEntity = productController.delete(id);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

}
