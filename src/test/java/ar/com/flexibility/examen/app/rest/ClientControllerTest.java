package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String URL = "/clients";

    @Test
    public void createClient() {
        ClientApi clientApi = new ClientApi();
        clientApi.setName("John");

        ResponseEntity<ClientApi> responseEntity = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(clientApi.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void retrieveClient() {
        ClientApi clientApi = new ClientApi();
        clientApi.setName("John");

        ResponseEntity<ClientApi> created = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<ClientApi> responseEntity = testRestTemplate.getForEntity(URL + "/" + created.getBody().getId(), ClientApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientApi.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void listClients() {
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity("/clients", List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void removeClient() {
        ClientApi clientApi = new ClientApi();
        clientApi.setName("John");

        ResponseEntity<ClientApi> created = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<ClientApi> responseEntity = testRestTemplate.exchange(URL + "/" + created.getBody().getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(clientApi),
                ClientApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void updateClient() {
        ClientApi clientApi = new ClientApi();
        clientApi.setName("John");

        ResponseEntity<ClientApi> created = testRestTemplate.postForEntity(URL, clientApi, ClientApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<ClientApi> responseEntity = testRestTemplate.exchange(URL + "/" + created.getBody().getId(),
                HttpMethod.PUT,
                new HttpEntity<>(clientApi),
                ClientApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientApi.getName(), responseEntity.getBody().getName());
    }
}