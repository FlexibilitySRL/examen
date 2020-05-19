package ar.com.flexibility.examen.app.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.Client;

@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testClientController()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String url = "http://localhost:" + port + "/clients/";

		// Client table is empty
		ResponseEntity<Client[]> clientsResponse = restTemplate.getForEntity(new URI(url), Client[].class);
		assertTrue(clientsResponse.getBody().length == 0);

		// Insert one client
		ResponseEntity<String> result = restTemplate.postForEntity(new URI(url),
				new HttpEntity<>(new Client(-1L, "CLIENT")), String.class);
		Assert.assertEquals(201, result.getStatusCodeValue());
		Client firstClient = objectMapper.readValue(result.getBody(), Client.class);
		assertNotNull(firstClient.getId());
		assertEquals("CLIENT", firstClient.getClientData());

		// Client table cointains new client
		clientsResponse = restTemplate.getForEntity(new URI(url), Client[].class);
		assertTrue(clientsResponse.getBody().length == 1);

		// Update client
		firstClient.setClientData("UPDATED_CLIENT");
		Assert.assertEquals(200, restTemplate.exchange(new URI(url + firstClient.getId()), HttpMethod.PUT,
				new HttpEntity<>(firstClient), Client.class).getStatusCodeValue());
		ResponseEntity<Client> responseClient = restTemplate.getForEntity(new URI(url + firstClient.getId()),
				Client.class);
		assertEquals("UPDATED_CLIENT", responseClient.getBody().getClientData());

		// Delete client
		restTemplate.delete(new URI(url + firstClient.getId()));
		clientsResponse = restTemplate.getForEntity(new URI(url), Client[].class);
		assertTrue(clientsResponse.getBody().length == 0);
	}

}