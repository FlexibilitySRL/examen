package ar.com.plug.examen.domain.service;
;
import ar.com.plug.examen.app.DTO.CustomerDTO;
import ar.com.plug.examen.app.mapper.CustomerMapper;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper mapper;

    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        customerService = new CustomerServiceImpl(customerRepository, mapper);
    }

    @Test
    public void testAddCustomer() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO(null, "Test Customer");
        Customer savedCustomer = new Customer(UUID.randomUUID(), "Test Customer");

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // Act
        customerService.addCustomer(customerDTO);

        // Assert
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testUpdateCustomer() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        CustomerDTO updatedCustomerDTO = new CustomerDTO(customerId, "Updated Customer");
        Customer existingCustomer = new Customer(customerId, "Original Customer");
        Customer updatedCustomer = new Customer(customerId, updatedCustomerDTO.name());

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);
        when(mapper.asDTO(any(Customer.class))).thenReturn(updatedCustomerDTO);

        // Act
        CustomerDTO actualCustomerDTO = customerService.updateCustomer(customerId, updatedCustomerDTO);

        // Assert
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals("Updated Customer", actualCustomerDTO.name());
    }

    @Test
    public void testDeleteCustomer() {
        // Arrange
        UUID customerId = UUID.randomUUID();

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        List<Customer> customerList = Arrays.asList(
                new Customer(UUID.randomUUID(), "Customer 1"),
                new Customer(UUID.randomUUID(), "Customer 2")
        );

        when(customerRepository.findAll()).thenReturn(customerList);

        // Act
        List<CustomerDTO> result = customerService.getAllCustomers();

        // Assert
        verify(customerRepository, times(1)).findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetCustomerById() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "Test Customer");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        Optional<CustomerDTO> result = customerService.getCustomerById(customerId);

        // Assert
        verify(customerRepository, times(1)).findById(customerId);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(customerId, result.get().id());
    }
}

