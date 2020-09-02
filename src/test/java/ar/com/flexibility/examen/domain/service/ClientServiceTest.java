package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;
    
    @Test
    public void createClient()
    {
        ClientApi clientApi = new ClientApi();
        clientApi.setName("Test");
        ClientApi clientApiNew = clientService.create(clientApi);

        assertNotNull(clientApiNew);
        assertNotNull(clientApiNew.getId());
    }
    
    @Test
    public void getClient()
    {
        ClientApi clientApi = clientService.get(1l);
        assertNotNull(clientApi);
    }
    
    @Test
    public void getClients()
    {
        List<ClientApi> clients = clientService.getClients();
        assertNotNull(clients);
    }
    
    @Test
    public void updateClient()
    {
        ClientApi clientApi = new ClientApi();
        clientApi.setName("Test2");
        ClientApi clientApiNew = clientService.update(1l, clientApi);

        assertNotNull(clientApiNew);
        assertEquals(clientApiNew.getName(), "Test2");
    }
    
    @Test
    public void deleteClient()
    {
        clientService.delete(1l);
        ClientApi clientApi = clientService.get(1l);
        assertNull(clientApi);
    }
}
