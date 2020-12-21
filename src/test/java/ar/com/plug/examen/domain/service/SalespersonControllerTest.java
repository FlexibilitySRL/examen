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

import ar.com.plug.examen.app.rest.SalespersonController;
import ar.com.plug.examen.domain.model.Salesperson;

@RunWith(MockitoJUnitRunner.class)
public class SalespersonControllerTest {
	@InjectMocks
	SalespersonController salespersonController;

	@Mock
	SalespersonService salespersonService;

	@Test
	public void testNewSalesperson() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Salesperson salesperson = new Salesperson(1, "Pepe", "Pompin", "test@plug.com", null);
		ResponseEntity<?> responseEntity = salespersonController.newSalesperson(salesperson);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void testOne() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		long id = 1;
		ResponseEntity<?> responseEntity = salespersonController.one(id);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testAll() {
		// Salesperson salesperson = new Salesperson(1, "PFIZER S.A:", "222",
		// "vacuna@pfizer.com", "123456789", null);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		ResponseEntity<?> responseEntity = salespersonController.all();

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

		Salesperson salesperson = new Salesperson(1, "Pepe", "Pompin", "test@plug.com", null);
		ResponseEntity<?> responseEntity = salespersonController.update(salesperson);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		// assertEquals(responseEntity.getHeaders().getLocation().getPath(), "/1");
	}

	@Test
	public void testDelete() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		long id = 1;
		ResponseEntity<?> responseEntity = salespersonController.delete(id);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

}
