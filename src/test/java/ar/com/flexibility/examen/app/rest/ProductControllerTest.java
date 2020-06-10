package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
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
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String URL = "/products";

    @Test
    public void createProduct() {
        ProductApi productApi = new ProductApi();
        productApi.setName("John");

        ResponseEntity<ProductApi> responseEntity = testRestTemplate.postForEntity(URL, productApi, ProductApi.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(productApi.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void retrieveProduct() {
        ProductApi productApi = new ProductApi();
        productApi.setName("John");

        ResponseEntity<ProductApi> created = testRestTemplate.postForEntity(URL, productApi, ProductApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<ProductApi> responseEntity = testRestTemplate.getForEntity(URL + "/" + created.getBody().getId(), ProductApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productApi.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void listProducts() {
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity("/clients", List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void removeProduct() {
        ProductApi productApi = new ProductApi();
        productApi.setName("John");

        ResponseEntity<ProductApi> created = testRestTemplate.postForEntity(URL, productApi, ProductApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<ProductApi> responseEntity = testRestTemplate.exchange(URL + "/" + created.getBody().getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(productApi),
                ProductApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void updateProduct() {
        ProductApi productApi = new ProductApi();
        productApi.setName("John");

        ResponseEntity<ProductApi> created = testRestTemplate.postForEntity(URL, productApi, ProductApi.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        ResponseEntity<ProductApi> responseEntity = testRestTemplate.exchange(URL + "/" + created.getBody().getId(),
                HttpMethod.PUT,
                new HttpEntity<>(productApi),
                ProductApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productApi.getName(), responseEntity.getBody().getName());
    }
}