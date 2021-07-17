package ar.com.plug.examen.domain.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.ArgumentMatchers.any;


import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.builderPattern.ClientBuilder;
import ar.com.plug.examen.domain.builderPattern.ClientDTOBuilder;
import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.service.IClientRepo;
import ar.com.plug.examen.entities.Client;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ClientControllerTest {

	@MockBean
	private IClientRepo clientService;

	@Autowired
	private TestRestTemplate restTemplate;

	private final String URL = "/client/";


	@Test
	public void saveClientTest() throws Exception {
//		Client c = new Client(1L, "lea", "ferreyra", "lean224", null);
		Client c = new ClientBuilder().withID(1L).withFirstname("Lea").withLastname("Ferreyra").withEmail("lean224").build();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Client> request = new HttpEntity<>(c, headers);
		ResponseEntity<ClientDTO> response = restTemplate.postForEntity(URL, request, ClientDTO.class);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	public void findAllClientTest() {
		ClientDTO c = new ClientDTOBuilder().withID(1L).withFirstname("Leandro").withLastname("Ferreyra").withEmail("lean224").build();
		List<ClientDTO> clients = Stream.of(c).collect(Collectors.toList());
		when(this.clientService.findAll()).thenReturn(clients);
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		assertTrue(responseEntity.getBody().size() == 1);
	    verify(this.clientService, times(1)).findAll();
	}

	@Test
	public void deleteClientTest() throws ResourceNotFoundError {
		restTemplate.delete(URL + "1");
		verify(this.clientService, times(1)).delete(1L);
	}

	@Test
	public void findClientByIDTestReturnsClientDTO() throws ResourceNotFoundError {
		ClientDTO c = new ClientDTOBuilder().withID(1L).withFirstname("Lea").withLastname("Ferreyra").withEmail("lean224").build();
		when(this.clientService.findClientById(1L)).thenReturn(c);
		ResponseEntity<ClientDTO> responseEntity = restTemplate.getForEntity(URL + "/1", ClientDTO.class);
		assertEquals(c, responseEntity.getBody());
		verify(this.clientService, times(1)).findClientById(1L);
	}

	@Test
	public void findClientByIDTestReturnsOK() throws ResourceNotFoundError {
		ClientDTO c = new ClientDTOBuilder().withID(1L).withFirstname("Lea").withLastname("Ferreyra").withEmail("lean224").build();
		when(this.clientService.findClientById(1L)).thenReturn(c);
		ResponseEntity<ClientDTO> responseEntity = restTemplate.getForEntity(URL + "/1", ClientDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void updateClientDTOReturnsOK() throws ResourceNotFoundError, BadRequestError {
		ClientDTO c = new ClientDTOBuilder().withID(1L).withFirstname("Lea").withLastname("Ferreyra").withEmail("lean224").build();
		when(this.clientService.update(any())).thenReturn(c);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ClientDTO> request = new HttpEntity<>(c, headers);
		ResponseEntity<ClientDTO> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, request, ClientDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(this.clientService, times(1)).update(c);
	}

}
