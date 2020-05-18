package ar.com.flexibility.examen.domain.service;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.domain.model.Purchase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:test-config.yml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PurchaseServiceTest {

    @LocalServerPort
    private int port;
    
    @Before
    public void beforeTest() throws Exception {
        RestAssured.baseURI = String.format("http://localhost:%d/purchase", port);
    }

    @Autowired
    public DataSource dataSource;
    
    private static Long idObjectTest; 

    @Test
    public void test1InsertPurchase(){
    	Purchase newPurchase = new Purchase();
    	newPurchase.setApprove(false);
        Response response =  given().contentType(ContentType.JSON).body(newPurchase).post("/");
        assertNotNull(response.getBody());
        assertEquals(201, response.getStatusCode());
        idObjectTest = response.getBody().as(Purchase.class).getId();
    }
    
    
    @Test
    public void test2GetPurchases() {
    	given().get("/")
	        .then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }
    
    @Test
    public void test3GetPurchaseById(){
    	given().get("/"+idObjectTest)
	        .then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }
    
    @Test
    public void test4ApprovePurchase(){
    	given().put("/"+idObjectTest+"/approve")
	    	.then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }

}
