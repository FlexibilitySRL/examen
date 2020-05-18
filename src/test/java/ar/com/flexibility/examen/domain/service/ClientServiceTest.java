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

import ar.com.flexibility.examen.domain.model.Client;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


/*
 * Cambie MockitoJUnitRunner por SpringRunner ya que con este ultimo tambien se puede testar la capa de rest,
 * ademas inclui una libreria llamada io.restassured que utiliza un DSL para invocar servicios rest y levanatr el Spring Boot en vivo
*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:test-config.yml")

//La etiqueta @FixMethodOrder permite que se ejecute primero el test de insercion para que luego se pueda consulta, editar y borrar

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientServiceTest {

    @LocalServerPort
    private int port;
    
    @Before
    public void beforeTest() throws Exception {
        RestAssured.baseURI = String.format("http://localhost:%d/client", port);
    }

    @Autowired
    public DataSource dataSource;
    
    private static Long idObjectTest; 

    @Test
    public void test1InsertClient(){
    	Client newClient = new Client();
    	newClient.setName("prueba");
        Response response =  given().contentType(ContentType.JSON).body(newClient).post("/");
        assertNotNull(response.getBody());
        assertEquals(201, response.getStatusCode());
        idObjectTest = response.getBody().as(Client.class).getId();
    }
    
    
    @Test
    public void test2GetClients() {
    	given().get("/")
	        .then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }
    
    @Test
    public void test3GetClientById(){
    	given().get("/"+idObjectTest)
	        .then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }
    
    @Test
    public void test4UpdateClient(){
    	Client client = new Client();
    	client.setEmail("prueba@prueba.com");
    	given().contentType(ContentType.JSON).body(client).put("/"+idObjectTest)
	    	.then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }
    
    @Test
    public void test5DeleteClient(){
    	given().delete("/"+idObjectTest)
	    	.then()
	        .assertThat()
	        .statusCode(200)
	        .contentType(ContentType.JSON);
    }

}
