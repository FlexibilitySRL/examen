package ar.com.flexibility.examen.service;

import ar.com.flexibility.examen.SpringConfig;
import ar.com.flexibility.examen.app.dto.CustomerDTO;
import ar.com.flexibility.examen.model.Customer;
import ar.com.flexibility.examen.model.repository.CustomerRepository;
import ar.com.flexibility.examen.service.impl.CustomerServiceImpl;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= SpringConfig.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Customer customer;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void givenCustomer_whenSave_thenGetOk(){
        final CustomerDTO customerDTO = new CustomerDTO(1L,"David","Test","davmb5@gmail.com", "Maipu 28");

        given(customerRepository.findById(customerDTO.getId())).willReturn(Optional.empty());
        given(customerRepository.save(customer)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given( modelMapper.map(customerDTO,Customer.class)).willReturn(customer);

        customerService.createCustomer(customerDTO);
        Optional<Customer> savedCustomer = customerRepository.findById(1L);

        assertNotNull(savedCustomer);
        verify(customerRepository).save(any(Customer.class));

    }

    @Test
    public void givenCustomer_whenUpdate_thenGetOk(){
        final CustomerDTO customerDTO = new CustomerDTO(1L,"David","Test","davmb5@gmail.com", "Maipu 28");

        given(customerRepository.save(customer)).willReturn(customer);
        given( modelMapper.map(customerDTO,Customer.class)).willReturn(customer);

        customerService.updateCustomer(1L, customerDTO);
        final Optional<Customer> expectedCustomer = customerRepository.findById(1L);

        assertNotNull(expectedCustomer);
        verify(customerRepository).save(any(Customer.class));

    }

    @Test
    public void givenCustomer_whenDelete_thenGetOk(){
        final Long customerId = 1L;

        customerService.deleteCustomerById(customerId);
        customerService.deleteCustomerById(customerId);

        verify(customerRepository, times(2)).deleteById(customerId);
    }
}
