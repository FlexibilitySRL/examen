package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		final Customer objectToCreate = new Customer("julian", "Manfredi");
		final Customer objectCreated = new Customer(1L, "julian", "Manfredi");

		when(customerRepository.save(objectToCreate)).thenReturn(objectCreated);

		Customer result = customerService.create(objectToCreate);
		assertEquals(objectCreated.getId(), result.getId());
	}

	
	@Test
	public void update() {
		final Customer objectToUpdate = new Customer(2L,"julian", "Manfredi");
		final Customer objectUpdated = new Customer(2L, "julian", "leon");

		when(customerRepository.save(objectToUpdate)).thenReturn(objectUpdated);

		Customer result = customerService.update(2L,objectToUpdate);

		assertNotEquals(result.getLastName(), objectToUpdate.getLastName());

	}
	
	
	@Test
	public void findById() {
		final Customer customer = new Customer(1L, "julian", "Manfredi");

		Optional<Customer> objectCreated = Optional.of(customer);

		when(customerRepository.findById(customer.getId())).thenReturn(
				objectCreated);

		Optional<Customer> result = customerService.getCustomerById(1L);

		assertEquals(objectCreated, result);

	}

	@Test
	public void findAll() {
		List<Customer> objectCreated = new ArrayList<>();

		objectCreated.add(new Customer(1L, "julian", "Manfredi"));
		objectCreated.add(new Customer(2L, "hector", "Monroe"));

		when(customerRepository.findAll()).thenReturn(objectCreated);

		List<Customer> result = customerService.getCustomers();

		assertEquals(2, result.size());

	}

 
}
