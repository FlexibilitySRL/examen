package ar.com.plug.examen.app.integration.rest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.api.ClientApi;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ClientControllerTest {
	
	@Autowired
    private TestRestTemplate testRestTemplate;
    private final String URL = "/client";
    
    
    @Test
    public void createClient() {
    	ClientApi clientApi = new ClientApi();
    	
    	clientApi.setName("test Name");
    	
    	ResponseEntity<ClientApi> response = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
    	
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	assertEquals(clientApi.getName(), response.getBody().getName());
    }
    
    @Test
    public void getClient()  throws Exception {
    	
    	ClientApi clientApi = new ClientApi();
    	clientApi.setName("test Name");
    	ResponseEntity<ClientApi> response = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	

        ResponseEntity<ClientApi> responseEntity = testRestTemplate.getForEntity(URL + "/" + response.getBody().getId(), ClientApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientApi.getName(), responseEntity.getBody().getName());
    }
    
    @Test
    public void listAllClients()  throws Exception {
    	
    	ClientApi clientApi1 = new ClientApi();
    	clientApi1.setName("test Name");
    	ResponseEntity<ClientApi> response1 = testRestTemplate.postForEntity(URL, clientApi1, ClientApi.class);
    	assertEquals(HttpStatus.CREATED, response1.getStatusCode());
    	
    	ClientApi clientApi2 = new ClientApi();
    	clientApi2.setName("test Name");
    	ResponseEntity<ClientApi> response2 = testRestTemplate.postForEntity(URL, clientApi2, ClientApi.class);
    	assertEquals(HttpStatus.CREATED, response2.getStatusCode());
    	
    	
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity(URL, List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void removeClient() throws Exception {
    	
    	ClientApi clientApi = new ClientApi();
    	clientApi.setName("test Name");
    	ResponseEntity<ClientApi> response = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	
    	ResponseEntity<ClientApi> responseEntity = testRestTemplate.exchange(URL + "/" + response.getBody().getId(), 
    			HttpMethod.DELETE, new HttpEntity<>(clientApi), ClientApi.class);
    	
    	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void updateClient() throws Exception {
    	ClientApi clientApi = new ClientApi();
    	clientApi.setId(1L);
    	clientApi.setName("test Name");
    	ResponseEntity<ClientApi> response = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	
    	ResponseEntity<ClientApi> getResponse = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
    	
    	getResponse.getBody().setName("Updated Name");
    	
    	ResponseEntity<ClientApi> responseEntity = testRestTemplate.exchange(URL + "/" + getResponse.getBody().getId(),
                HttpMethod.PUT,
                new HttpEntity<>(getResponse),
                ClientApi.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(getResponse.getBody().getName(), responseEntity.getBody().getName());
    	
    }
    
    
}
