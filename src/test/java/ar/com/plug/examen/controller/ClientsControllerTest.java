package ar.com.plug.examen.controller;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.model.Clients;
import ar.com.plug.examen.model.Products;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientsControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveAllClients() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/clients"),
                HttpMethod.GET, entity, String.class);
        String expected = response.getBody();
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    public void testRetrieveAllClientsBadResponse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/clients"),
                HttpMethod.GET, entity, String.class);

        String expected = "{id:Course1,name:Spring,description:10Steps}";
        JSONAssert.assertNotEquals(expected, response.getBody(), true);
    }

    @Test
    public void testAddClient() {

        Clients client = new Clients("Pedro","Cardenas", 123L,123L,"Cra 1", true);
        HttpEntity<Clients> entity = new HttpEntity<Clients>(client, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/clients"),
                HttpMethod.POST, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);

    }

    @Test
    public void testDeleteClient() {
        HttpEntity<Clients> entity = new HttpEntity<Clients>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/clients/25"),
                HttpMethod.DELETE, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);
    }

    @Test
    public void testUpdateClient() throws JsonProcessingException {

        String client = "{\"name\":\"Camiseta Nueva\",\"lastName\":\"Millonarios xl\",\"document\":12345,\"phone\":12345,\"address\":\"Cra 1\",\"state\":true,\"id\":24}";

        Clients clientObj = new ObjectMapper().readValue(client, Clients.class);
        HttpEntity<Clients> entity = new HttpEntity<Clients>(clientObj, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/clients"),
                HttpMethod.PUT, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);

    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}