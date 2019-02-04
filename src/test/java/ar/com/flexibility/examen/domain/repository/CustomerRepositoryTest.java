package ar.com.flexibility.examen.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.domain.model.Customer;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRepositoryTest {
	
	private Customer customer;
	static Long persistedId;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Before
	public void setup() {
		customer = new Customer();
		customer.setCustomerName("Avelardo Gavide");
		customer.setAddress("Parque Patricios");
		customer.setEmail("avelardog@gmail.com");
	}
	
	@Test
	public void a_givenCustomerEntity_whenSave_thenReturnOK() throws Exception {
		Customer persistedCustomer = customerRepository.save(customer);
		
		persistedId = persistedCustomer.getId();
		
		assertNotNull(persistedCustomer);
		assertEquals(customer, persistedCustomer);
	}
	
	@Test
	public void b_givenIdCustomer_whenFindByIdCustomer_thenReturnOK() {
		Customer myCustomer = customerRepository.findOne(persistedId);
		
		assertNotNull(myCustomer);
		assertEquals(customer.getCustomerName(), myCustomer.getCustomerName());
	}
	
	@Test
	public void c_givenIdCustomer_whenDelete_thenReturnOK() {
		customerRepository.delete(persistedId);
		
		Customer deletedCustomer = customerRepository.findOne(persistedId);
		assertNull(deletedCustomer);
	}
}
