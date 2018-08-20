package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductServiceTest {

    private static final Boolean NON_DELETED_FLAG = false;

    @Mock
    Product entityMock;

    @Mock
    ProductRepository repository;

    @InjectMocks
    DefaultProductService service;

    @Before
    public void setUp() {
        service = new DefaultProductService(repository);
    }

    @Test
    public void listAllTest() {
        List<Product> clients = new ArrayList<>();
        clients.add(entityMock);
        when(repository.findByDeleted(NON_DELETED_FLAG)).thenReturn(clients);
        List<Product> result = service.listAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}