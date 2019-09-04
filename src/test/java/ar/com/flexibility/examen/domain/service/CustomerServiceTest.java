package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.flexibility.examen.app.errorCode.CustomerErrorCode;
import ar.com.flexibility.examen.app.exception.BusinessException;
import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.impl.CustomerServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	public void whenDTOToEntityShouldReturnEquivalentEntity() {
		CustomerDTO customerDTO = new CustomerDTO("juan.perez@tecso.coop", "Juan Perez", "20340252541");
		Customer customer = customerService.dtoToEntity(customerDTO);

		assertTrue(customerDTO.getCuit().equals(customer.getCuit()));
		assertTrue(customerDTO.getName().equals(customer.getName()));
		assertTrue(customerDTO.getEmail().equals(customer.getEmail()));
	}

	@Test
	public void whenEntityToDTOShouldReturnEquivalentDTO() {
		Customer customer = new Customer("juan.perez@tecso.coop", "Juan Perez", "20340252541");
		CustomerDTO customerDTO = customerService.entityToDto(customer);

		assertTrue(customerDTO.getCuit().equals(customer.getCuit()));
		assertTrue(customerDTO.getName().equals(customer.getName()));
		assertTrue(customerDTO.getEmail().equals(customer.getEmail()));
	}

	@Test(expected = BusinessException.class)
	public void whenCustomerDTOWithoutCuitEntityToDTOShouldReturnErrorCode() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCuit(null);
		try {
			customerService.dtoToEntity(customerDTO);
		} catch (BusinessException ex) {
			assertTrue(ex.getMessages()[0].equals(CustomerErrorCode.CUSTOMER_INVALID_CUIT.getDescription()));
			throw ex;
		}
	}

	@Test
	public void whenSaveThenReturnCustomer() {
		// given
		Customer newCustomer = new Customer("juan.perez@tecso.coop", "Juan Perez", "20340252541");
		CustomerDTO customerDTO = customerService.entityToDto(newCustomer);

		doReturn(newCustomer).when(customerRepository).save(any(Customer.class));

		// when
		CustomerDTO returnedDTO = customerService.save(customerDTO);

		// then
		assertTrue(returnedDTO.getCuit().equals(newCustomer.getCuit()));
		assertTrue(returnedDTO.getName().equals(newCustomer.getName()));
		assertTrue(returnedDTO.getEmail().equals(newCustomer.getEmail()));
	}

	@Test
	public void whenFindByIdThenReturnCustomer() {
		Customer newCustomer = new Customer("juan.perez@tecso.coop", "Juan Perez", "20340252541");

		doReturn(newCustomer).when(customerRepository).findOne(1L);

		CustomerDTO returnedDTO = customerService.findById(1L);

		assertTrue(returnedDTO.getCuit().equals(newCustomer.getCuit()));
		assertTrue(returnedDTO.getName().equals(newCustomer.getName()));
		assertTrue(returnedDTO.getEmail().equals(newCustomer.getEmail()));
	}

	@Test
	public void whenFindAllThenReturnAllCustomers() {
		Customer newCustomer = new Customer("juan.perez@tecso.coop", "Juan Perez", "20340252541");

		List<CustomerDTO> actualCustomers = new ArrayList<CustomerDTO>();
		List<Customer> expectedCustomers = new ArrayList<Customer>();
		expectedCustomers.add(newCustomer);

		doReturn(expectedCustomers).when(customerRepository).findAll();

		actualCustomers = customerService.findAll();

		assertTrue(actualCustomers.get(0).getCuit().equals(expectedCustomers.get(0).getCuit()));
		assertTrue(actualCustomers.get(0).getEmail().equals(expectedCustomers.get(0).getEmail()));
		assertTrue(actualCustomers.get(0).getName().equals(expectedCustomers.get(0).getName()));
	}

}
