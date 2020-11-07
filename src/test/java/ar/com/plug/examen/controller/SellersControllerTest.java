package ar.com.plug.examen.controller;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.model.Sellers;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SellersControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testRetrieveAllSellers() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/sellers"),
                HttpMethod.GET, entity, String.class);
        String expected = response.getBody();
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }
    @Test
    public void testRetrieveAllSellersBadResponse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/sellers"),
                HttpMethod.GET, entity, String.class);

        String expected = "{id:Course1,name:Spring,description:10Steps}";
        JSONAssert.assertNotEquals(expected, response.getBody(), true);
    }

    @Test
    public void testAddSeller() {

        Sellers seller = new Sellers("Luis","Gonzalez", 123L,123L, true);
        HttpEntity<Sellers> entity = new HttpEntity<Sellers>(seller, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/sellers"),
                HttpMethod.POST, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);

    }

    @Test
    public void testUpdateSeller() throws JsonProcessingException {

        String seller = "{\"name\":\"Camiseta Nueva\",\"lastname\":\"Millonarios xl\",\"document\":12345678910,\"phone\":12345678910,\"state\":true,\"id\":31}";

        Sellers sellerObj = new ObjectMapper().readValue(seller, Sellers.class);
        HttpEntity<Sellers> entity = new HttpEntity<Sellers>(sellerObj, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/sellers"),
                HttpMethod.PUT, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);

    }

    @Test
    public void testDeleteSeller() {
        HttpEntity<Sellers> entity = new HttpEntity<Sellers>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/sellers/31"),
                HttpMethod.DELETE, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);
    }

}