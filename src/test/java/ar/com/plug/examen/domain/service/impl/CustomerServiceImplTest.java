/**
 * 
 */
package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.plug.examen.domain.model.Customer;

/**
 * @author hellraiser
 *
 */
@SpringBootTest
public class CustomerServiceImplTest {

	@Autowired
	CustomerServiceImpl customerService;

	/**
	 * Test method for
	 * {@link ar.com.plug.examen.domain.service.impl.customerServiceImpl#createCustomer(ar.com.plug.examen.domain.model.customer)}.
	 */
	@Test
	public void when_creatingNewCustomer_then_savedParametersMatch() throws Exception {

		Customer customer = new Customer();
		customer.setFirstName("Martín");
		customer.setLastName("Fierro");

		Customer customer2 = customerService.createCustomer(customer);
		assertEquals("El nombre debe coincidir", customer.getFirstName(), customer2.getFirstName());
		assertEquals("El apellido debe coincidir", customer.getLastName(), customer2.getLastName());

	}

}
