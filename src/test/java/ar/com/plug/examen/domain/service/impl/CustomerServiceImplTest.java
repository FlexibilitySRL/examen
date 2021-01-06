package ar.com.plug.examen.domain.service.impl;


import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.domain.common.EmailServiceValidator;
import ar.com.plug.examen.domain.common.impl.RequestTool;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.infrastructure.exception.CustomerEmailExistException;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static  org.mockito.Mockito.when;
import static  org.mockito.Mockito.any;


@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl service;
    @Mock
    private CustomerRepository repository;
    @Mock
    private EmailServiceValidator emailValidator;

    @Test(expected = CustomerEmailExistException.class)
    public void saveDuplicateEmail() throws CustomerEmailExistException {
        when(emailValidator.validateEmail()).thenReturn(test -> true);
        service.save(customer());
    }

    @Test
    public void save() throws CustomerEmailExistException {
        when(emailValidator.validateEmail()).thenReturn(test -> false);
        when(repository.save(any())).thenReturn(RequestTool.swapCustomer(customer()));
        CustomerApi customer = service.save(customer());
        Assert.assertEquals(customer.getEmail(), "er@r.com");
        Assert.assertEquals(customer.getAddress(), "BA");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateDontExistCustomer() throws ResourceNotFoundException, CustomerEmailExistException {
        when(repository.findCustomerById(1l)).thenReturn(Optional.empty());
        service.update(customer(), 1l);
    }

    @Test(expected = CustomerEmailExistException.class)
    public void updateDuplicateEmail() throws ResourceNotFoundException, CustomerEmailExistException {
        when(repository.findCustomerById(any())).thenReturn(Optional.of(RequestTool.swapCustomer(customer())));
        when(emailValidator.validateEmailUpdate()).thenReturn((a,b) -> true);
        service.update(customer(), 1l);
    }

    @Test
    public void update() throws ResourceNotFoundException, CustomerEmailExistException {
        when(repository.findCustomerById(any())).thenReturn(Optional.of(RequestTool.swapCustomer(customer())));
        when(emailValidator.validateEmailUpdate()).thenReturn((a,b) -> false);
        when(repository.save(any())).thenReturn(RequestTool.swapCustomer(customer()));
        CustomerApi customer = service.update(customer(), 1l);
        Assert.assertEquals(customer.getEmail(), "er@r.com");
        Assert.assertEquals(customer.getAddress(), "BA");

    }

    @Test(expected = ResourceNotFoundException.class )
    public void deleteDontExistCustomer() throws ResourceNotFoundException {
        when(repository.findCustomerById(1l)).thenReturn(Optional.empty());
        service.delete(1l);
    }

    @Test
    public void deleteFalse() throws ResourceNotFoundException {
        when(repository.findCustomerById(any())).thenReturn(Optional.of(RequestTool.swapCustomer(customer())));
        when(repository.deleteCustomer(any())).thenReturn(0);
        Assert.assertFalse(service.delete(1l));
    }

    @Test
    public void delete() throws ResourceNotFoundException {
        when(repository.findCustomerById(any())).thenReturn(Optional.of(RequestTool.swapCustomer(customer())));
        when(repository.deleteCustomer(any())).thenReturn(1);
        Assert.assertTrue(service.delete(1l));
    }

    private CustomerApi customer(){
        CustomerApi customer = new CustomerApi();
        customer.setSex("m");
        customer.setLastName("perez");
        customer.setName("pedro");
        customer.setDocument("12345");
        customer.setAge("21");
        customer.setAddress("BA");
        customer.setEmail("er@r.com");
        return customer;
    }
}