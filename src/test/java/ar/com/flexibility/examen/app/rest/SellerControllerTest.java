package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SellerApi;
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
public class SellerControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String URL = "/sellers";

    @Test
    public void createSeller() {
        SellerApi sellerApi = new SellerApi();
        sellerApi.setName("John");

        ResponseEntity<SellerApi> responseEntity = testRestTemplate.postForEntity(URL, sellerApi, SellerApi.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(sellerApi.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void retrieveSeller() {
        SellerApi sellerApi = new SellerApi();
        sellerApi.setName("John");

        ResponseEntity<SellerApi> created = testRestTemplate.postForEntity(URL, sellerApi, SellerApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<SellerApi> responseEntity = testRestTemplate.getForEntity(URL + "/" + created.getBody().getId(), SellerApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sellerApi.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void listSellers() {
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity("/clients", List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void removeSeller() {
        SellerApi sellerApi = new SellerApi();
        sellerApi.setName("John");

        ResponseEntity<SellerApi> created = testRestTemplate.postForEntity(URL, sellerApi, SellerApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<SellerApi> responseEntity = testRestTemplate.exchange(URL + "/" + created.getBody().getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(sellerApi),
                SellerApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void updateSeller() {
        SellerApi sellerApi = new SellerApi();
        sellerApi.setName("John");

        ResponseEntity<SellerApi> created = testRestTemplate.postForEntity(URL, sellerApi, SellerApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<SellerApi> responseEntity = testRestTemplate.exchange(URL + "/" + created.getBody().getId(),
                HttpMethod.PUT,
                new HttpEntity<>(sellerApi),
                SellerApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sellerApi.getName(), responseEntity.getBody().getName());
    }
}