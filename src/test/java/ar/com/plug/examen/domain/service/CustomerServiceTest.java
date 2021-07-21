package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repositories.ICustomerRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;

    @Test
    public void findCustomer() {
        Customer customer = new Customer("Nahuel", "Perez", "nahuel.perez@gmail.com");
        when(customerRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(customer));
        Customer result = customerRepository.findById(1L).get();
        Assertions.assertEquals(customer, result);
    }

    @Test
    public void findAllCustomers() {
        Customer customer = new Customer("Nahuel", "Perez", "nahuel.perez@gmail.com");
        Customer customer2 = new Customer("Juan", "Perez", "juan.perez@gmail.com");

        ArrayList<Customer> lCustomers = new ArrayList<>();
        lCustomers.add(customer);
        lCustomers.add(customer2);

        when(customerRepository.findAll()).thenReturn(lCustomers);

        Assertions.assertEquals(customerRepository.findAll().spliterator().getExactSizeIfKnown(), 2);
    }

    @Test
    public void saveCustomer() {
        Customer customer = new Customer("Nahuel", "Perez", "nahuel.perez@gmail.com");

        when(customerRepository.save(Mockito.any())).thenReturn(customer);

        Customer savedCustomer = customerRepository.save(customer);

        Assertions.assertEquals(customer, savedCustomer);
    }
}
