package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.PurchaseApi;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Optional;

import static  org.mockito.Mockito.when;
import static  org.mockito.Mockito.any;

@RunWith(SpringRunner.class)
public class PurchaseServiceImplTest {

    @InjectMocks
    private PurchaseServiceImpl service;

    @Mock
    private PurchaseRepository repository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void save() throws ResourceNotFoundException {

        Product product = new Product.Builder().withId(1l).withBrand("BA").build();
        Customer customer = new Customer.Builder().withId(1l).build();
        Purchase purchaseEntity = new Purchase.Builder()
                .withIdCustomer(customer)
                .withIdProduct(product)
                .withIdWorker(1l)
                .withCreated(new Date(System.currentTimeMillis()))
                .build();
        when(productRepository.findProductById(any())).thenReturn(Optional.of(new Product.Builder().build()));
        when(customerRepository.findCustomerById(any())).thenReturn(Optional.of(new Customer.Builder().build()));
        when(repository.save(any())).thenReturn(purchaseEntity);
        PurchaseApi p = service.save(purchase());
        Assert.assertEquals(p.getIdWorker().longValue(), 1l);
        Assert.assertEquals(p.getIdProduct().longValue(), 1l);
    }




    @Test(expected = ResourceNotFoundException.class)
    public void saveNotExistCustomer() throws ResourceNotFoundException {
        when(productRepository.findProductById(any())).thenReturn(Optional.of(new Product.Builder().build()));
        when(customerRepository.findCustomerById(any())).thenReturn(Optional.empty());
        service.save(purchase());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void saveNotExistProduct() throws ResourceNotFoundException {
        when(productRepository.findProductById(any())).thenReturn(Optional.empty());
        service.save(purchase());
    }

    @Test
    public void getTransaction() {

    }

    private PurchaseApi purchase() {
        PurchaseApi purchase = new PurchaseApi();
        purchase.setIdCustomer(1l);
        purchase.setIdWorker(1l);
        purchase.setIdProduct(1l);
        return purchase;
    }
}