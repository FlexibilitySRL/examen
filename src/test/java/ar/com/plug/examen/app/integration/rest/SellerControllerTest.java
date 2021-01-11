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
import ar.com.plug.examen.app.api.SellerApi;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class SellerControllerTest {
	
	@Autowired
    private TestRestTemplate testRestTemplate;
    private final String URL = "/seller";
    
    @Autowired
    WebApplicationContext webApplicationContext;


    
    @Test
    public void createSeller() {
    	SellerApi sellerApi = new SellerApi();
    	
    	sellerApi.setName("test Name");
    	
    	ResponseEntity<SellerApi> response = testRestTemplate.postForEntity(URL, sellerApi, SellerApi.class);
    	
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	assertEquals(sellerApi.getName(), response.getBody().getName());
    }
    
    @Test
    public void getSeller()  throws Exception {
    	
    	SellerApi sellerApi = new SellerApi();
    	sellerApi.setName("test Name");
    	ResponseEntity<SellerApi> response = testRestTemplate.postForEntity(URL, sellerApi, SellerApi.class);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	

        ResponseEntity<SellerApi> responseEntity = testRestTemplate.getForEntity(URL + "/" + response.getBody().getId(), SellerApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sellerApi.getName(), responseEntity.getBody().getName());
    }
    
    @Test
    public void listAllSellers()  throws Exception {
    	
    	SellerApi sellerApi1 = new SellerApi();
    	sellerApi1.setName("test Name");
    	ResponseEntity<SellerApi> response1 = testRestTemplate.postForEntity(URL, sellerApi1, SellerApi.class);
    	assertEquals(HttpStatus.CREATED, response1.getStatusCode());
    	
    	SellerApi sellerApi2 = new SellerApi();
    	sellerApi2.setName("test Name");
    	ResponseEntity<SellerApi> response2 = testRestTemplate.postForEntity(URL, sellerApi2, SellerApi.class);
    	assertEquals(HttpStatus.CREATED, response2.getStatusCode());
    	
    	
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity(URL, List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void removeSeller() throws Exception {
    	
    	SellerApi sellerApi = new SellerApi();
    	sellerApi.setName("test Name");
    	ResponseEntity<SellerApi> response = testRestTemplate.postForEntity(URL, sellerApi, SellerApi.class);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	
    	ResponseEntity<SellerApi> responseEntity = testRestTemplate.exchange(URL + "/" + response.getBody().getId(), 
    			HttpMethod.DELETE, new HttpEntity<>(sellerApi), SellerApi.class);
    	
    	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void updateSeller() throws Exception {
    	SellerApi sellerApi = new SellerApi();
    	sellerApi.setId(1L);
    	sellerApi.setName("test Name");
    	ResponseEntity<SellerApi> response = testRestTemplate.postForEntity(URL, sellerApi, SellerApi.class);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	
    	ResponseEntity<SellerApi> responseEntity = testRestTemplate.exchange(URL + "/" + response.getBody().getId(),
                HttpMethod.PUT,
                new HttpEntity<>(sellerApi),
                SellerApi.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(sellerApi.getName(), responseEntity.getBody().getName());
    	
    }
    
    
}