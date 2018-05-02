package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AddClientTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void add()
    {
    }
}
