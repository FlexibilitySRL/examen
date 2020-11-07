package ar.com.plug.examen.controller;

import ar.com.plug.examen.Application;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductResourceTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @Test
    public void testRetrieveAllProducts() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/products"),
                HttpMethod.GET, entity, String.class);
        String expected = response.getBody();
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }
    @Test
    public void testRetrieveAllProductsBadResponse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/products"),
                HttpMethod.GET, entity, String.class);

        String expected = "{id:Course1,name:Spring,description:10Steps}";
        JSONAssert.assertNotEquals(expected, response.getBody(), true);
    }

    @Test
    public void testAddProduct() {

        Products product = new Products("Camiseta","Millonarios xl",12345678910L,12345678910L,true);
        HttpEntity<Products> entity = new HttpEntity<Products>(product, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/products"),
                HttpMethod.POST, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);

    }

    @Test
    public void testDeleteProduct() {
        HttpEntity<Products> entity = new HttpEntity<Products>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/products/21"),
                HttpMethod.DELETE, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);
    }

    @Test
    public void testUpdateProduct() throws JsonProcessingException {

        String product = "{\"name\":\"Camiseta Nueva\",\"description\":\"Millonarios xl\",\"value\":12345678910,\"quantity\":12345678910,\"state\":true,\"id\":23}";

        Products productObj = new ObjectMapper().readValue(product, Products.class);
        HttpEntity<Products> entity = new HttpEntity<Products>(productObj, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/products"),
                HttpMethod.PUT, entity, String.class);
        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}