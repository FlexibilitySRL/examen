package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.GenericEntity;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
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
public class DefaultClientServiceTest {

    private static final Boolean NON_DELETED_FLAG = false;

    @Mock
    Client entityMock;

    @Mock
    ClientRepository repository;

    @InjectMocks
    DefaultClientService service;

    @Before
    public void setUp() {
        service = new DefaultClientService(repository);
    }

    @Test
    public void listAllTest() {
        List<Client> clients = new ArrayList<>();
        clients.add(entityMock);
        when(repository.findByDeleted(NON_DELETED_FLAG)).thenReturn(clients);
        List<Client> result = service.listAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}