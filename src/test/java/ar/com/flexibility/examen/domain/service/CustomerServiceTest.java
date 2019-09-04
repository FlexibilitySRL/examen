package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.flexibility.examen.app.errorCode.CustomerErrorCode;
import ar.com.flexibility.examen.app.exception.BusinessException;
import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.impl.CustomerServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	public CustomerServiceImpl customerService;

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
			assertTrue(ex.getMessages()[0].equals(CustomerErrorCode.CUSTOMER_INVALID_CUIT.name()));
			throw ex;
		}
	}
}
