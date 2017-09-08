package ar.com.flexibility.examen.domain.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.impl.CustomerServiceImpl;

/**
 * 
 * @author hackma
 * @version 0.1 Test para prueba unitaria de la clase @see CustomerServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private Customer customer;

	@Test
	public void createCustomerTest() {
		customerServiceImpl.createCustomer(customer);
		when(customerServiceImpl.createCustomer(customer)).thenReturn(customer);
		verify(customerRepository).save(customer);
		
	}
	
	@Test
	public void findAllCustomersTest() {
		customerServiceImpl.findAllCustomers();
		when(customerServiceImpl.findAllCustomers()).thenReturn(new ArrayList<Customer>());
		verify(customerRepository).findAll();
	}
	
	@Test
	public void findCustomerById() {
		customerServiceImpl.findCustomerById(customer.getId());
		when(customerServiceImpl.findCustomerById(customer.getId())).thenReturn(customer);
		verify(customerRepository).findOne(customer.getId());
	}
	
	@Test
	public void updateCustomerTest() {
		when(customerRepository.exists(customer.getId())).thenReturn(true);
		customerServiceImpl.updateCustomer(customer);
		when(customerRepository.save(customer)).thenReturn(customer);
		verify(customerRepository).save(customer);
	}
	
	@Test
	public void deleteCustomerTest() throws Exception {
		customerServiceImpl.deleteCustomer(customer);
		verify(customerRepository).delete(customer);
	}

}