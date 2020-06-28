package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.model.CellPhone;
import ar.com.flexibility.examen.domain.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;
	
	@Rule
    public ExpectedException thrownException = ExpectedException.none();
	
	@Test
	public void createCustomer() {
		
		Address address = new Address("Argentina", "Buenos Aires", "Ituzaingo", "xxx", 2880);
		
		CellPhone cellPhone = new CellPhone();
		cellPhone.setNumber(12456789);
		
		Customer customer = new Customer("Dario", "Guzman", "dfguzman91@gmail.com");
		
		customer.addAddress(address);
		
		Customer customerSaved = customerService.save(customer);
		
		assertNotNull(customerSaved);
		assertEquals(customerSaved.getName(),customer.getName());
		assertEquals(customerSaved.getLastname(),customer.getLastname());
		assertNotNull(customerSaved.getId());
    }
	@Test
	public void delete(){
		
		Address address = new Address("Argentina", "Buenos Aires", "Ituzaingo", "xxxx", 2880);
		
		CellPhone cellPhone = new CellPhone();
		cellPhone.setNumber(12456789);
		
		Customer customer = new Customer("Darioo", "Guzman", "dfguzman913@gmail.com");
		
		customer.addAddress(address);
		
		Customer customerSaved = customerService.save(customer);
		
		customerService.deleteCustomer(customerSaved.getId());
		
		thrownException.expect(EntityNotFoundException.class);
		Assert.assertNull(customerService.getCustomerById(customerSaved.getId()));
		
		
	}
	@Test
    public void deleteNotFound() {
		
		thrownException.expect(DataAccessException.class);
		customerService.deleteCustomer(new Long(12));
    }
	
	@Test
	public void findCustomer(){
		
		Address address = new Address("Argentina", "Buenos Aires", "Ituzaingo", "xxx", 2880);
		
		CellPhone cellPhone = new CellPhone();
		cellPhone.setNumber(12456789);
		
		Customer customer = new Customer("Dario Fernando", "Guzman", "dfguzman912@gmail.com");
		
		customer.addAddress(address);
		
		Customer customerSaved = customerService.save(customer);
		
		Customer customerByEmail = customerService.getCustomerByEmail("dfguzman912@gmail.com");
		
		assertNotNull(customerByEmail);
		assertEquals(customerByEmail.getName(),customer.getName());
		assertEquals(customerByEmail.getLastname(),customer.getLastname());
		
		Customer customerById = customerService.getCustomerById(customerSaved.getId());
		
		assertNotNull(customerById);
		assertEquals(customerById.getName(),customer.getName());
		assertEquals(customerById.getLastname(),customer.getLastname());
	}
}
