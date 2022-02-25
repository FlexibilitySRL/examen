package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.enums.Result;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.LogTransation;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.LogTransationRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    Long idCustomer = 1L;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private LogTransationRepository logTransationRepository;
    @Mock
    private PurchaseRepository purchaseRepository;

    private List<Purchase> listPurchase = new ArrayList<>();


    @Test
    public void whenCreateCustomer_thenReturnsCustomer() {
        Customer customer = Customer.builder().idCustomer(1)
                .documentNumber("1245")
                .email("prueba@hotmail.com")
                .phone("312467477")
                .name("prueba")
                .lastName("prueba 2")
                .build();

        LogTransation LogTransation1 = new LogTransation();
        LogTransation1.setIdLogTransation(1);

        CustomerDTO customerDTO = CustomerDTO.builder().idCustomer(1)
                .email("prueba@hotmail.com")
                .name("Pablo")
                .lastName("Hincapie")
                .phone("3154789574")
                .documentNumber("145784578")
                .build();

        LogTransation logTransation = LogTransation.builder().module("guardar cliente")
                .Result(Result.SUCCESS).description("Se guardo correctamente").build();
        when(logTransationRepository.save(logTransation)).thenReturn(LogTransation1);
        when(customerRepository.saveAndFlush(ArgumentMatchers.any(Customer.class))).thenReturn(customer);
        customerService.createCustomer(customerDTO);
        assertThat(customer).isNotNull();

    }

    @Test
    public void whenDeleteCustomer_thenReturnsCustomer() {
        Customer customer = Customer.builder().idCustomer(1)
                .documentNumber("1245").build();
        given(customerRepository
                .findById(any()))
                .willReturn(Optional.ofNullable(customer));
        when(purchaseRepository.findProductByCustomerIdCustomerOrProductIdProductOrSellerIdSeller(idCustomer, null, null)).thenReturn(listPurchase);
        customerService.deleteCustomer(idCustomer);
        assertThat(customer).isNotNull();
    }

    @Test
    public void whenEditCustomer_thenReturnsCustomer() {
        Customer customer = Customer.builder().idCustomer(1)
                .documentNumber("1245").build();

        CustomerDTO customerDTO = CustomerDTO.builder().idCustomer(1L)
                .email("prueba@hotmail.com")
                .name("Pablo")
                .lastName("Hincapie")
                .phone("3154789574")
                .documentNumber("145784578")
                .build();
        given(customerRepository
                .findById(any()))
                .willReturn(Optional.ofNullable(customer));

        customerService.editCustomer(idCustomer, customerDTO);
        assertThat(customer).isNotNull();
    }


    @Test(expected = Exception.class)
    public void whenEditCustomerEmpty_thenReturnsExceptionDataCustomer() {
        Customer customer = Customer.builder().idCustomer(1)
                .documentNumber("1245").build();
        CustomerDTO customerDTO = CustomerDTO.builder().idCustomer(1L)
                .name("Pablo")
                .lastName("Hincapie")
                .phone("3154789574")
                .documentNumber("145784578")
                .build();

        Customer customer1 = new Customer();
        customer1.setIdCustomer(1);

        given(customerRepository.save(customer))
                .willReturn((customer1));
        given(customerRepository
                .findById(any()))
                .willReturn(Optional.ofNullable(customer));

        customerService.editCustomer(idCustomer, customerDTO);
        assertThrows(Exception.class, () -> customerService.editCustomer(idCustomer, customerDTO));
    }
}
