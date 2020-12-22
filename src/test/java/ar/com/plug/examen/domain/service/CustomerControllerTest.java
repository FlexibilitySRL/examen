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

import ar.com.plug.examen.app.rest.CustomerController;
import ar.com.plug.examen.domain.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	@Test
	public void testNewCustomer() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Customer customer = new Customer(1, "PFIZER S.A:", "222", "vacuna@pfizer.com", "123456789", null);
		ResponseEntity<?> responseEntity = customerController.newCustomer(customer);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
		// assertEquals(responseEntity.getHeaders().getLocation().getPath(), "/1");
	}

	@Test
	public void testOne() {
		// Customer customer = new Customer(1, "PFIZER S.A:", "222",
		// "vacuna@pfizer.com", "123456789", null);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		long id = 1;
		ResponseEntity<?> responseEntity = customerController.one(id);

		/*
		 * assertThat(result.getEmployeeList().size()).isEqualTo(2);
		 * assertThat(result.getEmployeeList().get(0).getFirstName()).isEqualTo(
		 * employee1.getFirstName());
		 */

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testAll() {
		// Customer customer = new Customer(1, "PFIZER S.A:", "222",
		// "vacuna@pfizer.com", "123456789", null);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		ResponseEntity<?> responseEntity = customerController.all();

		/*
		 * assertThat(result.getEmployeeList().size()).isEqualTo(2);
		 * assertThat(result.getEmployeeList().get(0).getFirstName()).isEqualTo(
		 * employee1.getFirstName());
		 */

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdate() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Customer customer = new Customer(1, "PFIZER S.A:", "222", "nuevomail@pfizer.com", "123456789", null);
		ResponseEntity<?> responseEntity = customerController.update(customer);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		// assertEquals(responseEntity.getHeaders().getLocation().getPath(), "/1");
	}

	@Test
	public void testDelete() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		long id = 1;
		ResponseEntity<?> responseEntity = customerController.delete(id);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

}
