package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
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
public class DefaultSellerServiceTest {

    private static final Boolean NON_DELETED_FLAG = false;

    @Mock
    Seller entityMock;

    @Mock
    SellerRepository repository;

    @InjectMocks
    DefaultSellerService service;

    @Before
    public void setUp() {
        service = new DefaultSellerService(repository);
    }

    @Test
    public void listAllTest() {
        List<Seller> sellers = new ArrayList<>();
        sellers.add(entityMock);
        when(repository.findByDeleted(NON_DELETED_FLAG)).thenReturn(sellers);
        List<Seller> result = service.listAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}