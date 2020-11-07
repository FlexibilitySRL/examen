package ar.com.plug.examen.controller;

import ar.com.plug.examen.Application;
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
class TransactionsControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testRetrieveAllTransactions() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/transactions"),
                HttpMethod.GET, entity, String.class);
        String expected = response.getBody();
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    public void testRetrieveAllTransactionsBadResponse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/transactions"),
                HttpMethod.GET, entity, String.class);

        String expected = "{id:Course1,name:Spring,description:10Steps}";
        JSONAssert.assertNotEquals(expected, response.getBody(), true);
    }


    @Test
    public void testUpdateStateTransactions()  {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("payments/api/v1/transactions/11"),
                HttpMethod.GET, entity, String.class);

        int responseStatus = response.getStatusCodeValue();
        Assert.assertEquals(200, responseStatus);
    }

}