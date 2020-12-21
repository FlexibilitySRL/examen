package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import ar.com.plug.examen.app.rest.OperationController;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Operation;
import ar.com.plug.examen.domain.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class OperationControllerTest {
	@InjectMocks
	OperationController operationController;

	@Mock
	OperationService operationService;

	@Test
	public void testNewOperation() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Customer customer = new Customer(1, "PFIZER S.A:", "222", "vacuna@pfizer.com", "123456789", null);
		Product product = new Product(44, "Producto 1", "Descripcion producto 1", (float) 1000.1, null);
		List<Product> products = new ArrayList<>();
		products.add(product);
		
		Operation operation = new Operation(1,LocalDateTime.now(),customer,products,(float) 1000, false);
		ResponseEntity<?> responseEntity = operationController.newOperation(operation);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
		// assertEquals(responseEntity.getHeaders().getLocation().getPath(), "/1");
	}

	@Test
	public void testOne() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		long id = 1;
		ResponseEntity<?> responseEntity = operationController.one(id);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testAll() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		ResponseEntity<?> responseEntity = operationController.all();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testAprove() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		long id = 1;
		ResponseEntity<?> responseEntity = operationController.aprove(id);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
	}

}
