package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.service.impl.ProcessClientServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessClientServiceTest {

    @Autowired
    private ProcessClientServiceImpl clientService;
    
    @Test
    public void shouldCreateAClient()
    {
        ClientApi clientApi = new ClientApi();
        clientApi.setFirstName("John");
        clientApi.setLastName("Williams");
        clientApi.setCategory("Gold");        
        
        clientApi = clientService.create(clientApi);
        
        assertNotNull(clientApi.getId());
        assertEquals(clientApi.getFirstName(), "John");
    }
    
    @Test
    public void shouldUpdateAClient() {
    	ClientApi clientApi;
    	clientApi = clientService.searchByName("John");
        clientApi.setFirstName("Carl");
        
        clientApi = clientService.update(clientApi.getId(), clientApi);
        
        assertNotNull(clientApi);
        assertEquals(clientApi.getFirstName(), "Carl");
    }
    
    @Test
    public void shouldDeleteAClient() {
    	ClientApi clientApi;
    	clientApi = clientService.searchByName("Carl");
    	clientService.delete(clientApi);
    	clientApi = clientService.searchByName("Carl");    	
    	assertNull(clientApi);
    }
}
