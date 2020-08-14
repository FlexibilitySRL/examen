package ar.com.flexibility.examen.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.com.flexibility.examen.app.rest.dto.CustomerRequestDto;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.impl.CustomerServiceImpl;
import ar.com.flexibility.examen.exception.DataValidationException;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	
	@Mock
	private CustomerRepository customerRepository;

    @Test
    public void updateCustomer_throwsException_WhenCustomerIsNull() {
    	CustomerRequestDto dto = new CustomerRequestDto();
    	dto.setId(1);
    	dto.setName("name");
    	when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    	
    	DataValidationException exception = assertThrows(DataValidationException.class, () ->  customerServiceImpl.updateCustomer(dto));
    	Assertions.assertNotNull(exception.getMessage());
    }
    
    @Test
    public void findCustomer() {
    	Customer customer = new Customer();
    	customer.setId(1);
    	customer.setName("name");
    	when(customerRepository.findByName(Mockito.anyString())).thenReturn(customer);
    	
    	Customer result = customerServiceImpl.findCustomer("name");
    	Assertions.assertEquals(customer, result);
    }
    
    @Test
    public void deleteCustomer_throwsException_WhenCustomerNotExist() {
    	CustomerRequestDto dto = new CustomerRequestDto();
    	dto.setId(1);
    	dto.setName("name");
    	when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    	
    	DataValidationException exception = assertThrows(DataValidationException.class, () ->  customerServiceImpl.deleteCustomer(1));
    	Assertions.assertNotNull(exception.getMessage());
    }
}