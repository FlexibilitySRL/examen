package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "/customers/";

    private CustomerDTO customerDTO;

    private CustomerDTO getCustomerDTO(){

        CustomerDTO customer = new CustomerDTO();
        customer = new CustomerDTO();
        customer.setId("78963412");
        customer.setName("Nombre1");
        customer.setLastName("Apellido1");
        customer.setEmail("correo@correo.com");
        customer.setPhoneNumber("3015421879");
        return customer;
    }

    @Test
    public void saveCustomer() {

        customerDTO = getCustomerDTO();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO, headers);
        ResponseEntity<CustomerDTO> response = restTemplate.postForEntity(BASE_URL + "save", request, CustomerDTO.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void updateCustomerOK() {

        customerDTO = getCustomerDTO();
        Mockito.when(this.customerService.update(Mockito.any())).thenReturn(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO, headers);
        ResponseEntity<CustomerDTO> response = restTemplate.exchange(BASE_URL + "update", HttpMethod.PUT,request, CustomerDTO.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void findCustomerById() {

        customerDTO = getCustomerDTO();

        Mockito.when(this.customerService.getById("78963412")).thenReturn(Optional.of(customerDTO));
        ResponseEntity<CustomerDTO> response = restTemplate.getForEntity(BASE_URL + customerDTO.getId(), CustomerDTO.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findAllCustomers() {

        customerDTO = getCustomerDTO();
        Mockito.when(this.customerService.getAll()).thenReturn(Arrays.asList(customerDTO));
        ResponseEntity<List> response = restTemplate.getForEntity(BASE_URL + "all", List.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().size() == 1);

    }
}