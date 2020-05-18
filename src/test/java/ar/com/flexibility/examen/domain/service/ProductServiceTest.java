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

import ar.com.flexibility.examen.domain.model.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:test-config.yml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest {

    @LocalServerPort
    private int port;
    
    @Before
    public void beforeTest() throws Exception {
        RestAssured.baseURI = String.format("http://localhost:%d/product", port);
    }

    @Autowired
    public DataSource dataSource;
    
    private static Long idObjectTest; 

    @Test
    public void test1InsertProduct(){
    	Product newProduct = new Product();
    	newProduct.setName("ProductoDePrueba");
        Response response =  given().contentType(ContentType.JSON).body(newProduct).post("/");
        assertNotNull(response.getBody());
        assertEquals(201, response.getStatusCode());
        idObjectTest = response.getBody().as(Product.class).getId();
    }
    
    
    @Test
    public void test2GetProducts() {
    	given().get("/")
	        .then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }
    
    @Test
    public void test3GetProductById(){
    	given().get("/"+idObjectTest)
	        .then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }
    
    @Test
    public void test4UpdateProduct(){
    	Product product = new Product();
    	product.setPrice(12.0F);
    	given().contentType(ContentType.JSON).body(product).put("/"+idObjectTest)
	    	.then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }
    
    @Test
    public void test5DeleteProduct(){
    	given().delete("/"+idObjectTest)
	    	.then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }

}
