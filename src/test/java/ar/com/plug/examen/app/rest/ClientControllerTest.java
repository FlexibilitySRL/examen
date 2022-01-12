package ar.com.plug.examen.app.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.api.client.ClientRequestApi;
import ar.com.plug.examen.app.api.client.ClientResponseApi;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class ClientControllerTest {

	private final String URL = "/clients";

	@InjectMocks
	private ClientController controller;

	@Autowired
	private TestRestTemplate testRest;

	@MockBean
	private ClientServiceImpl service;

	static final Client client = new Client();

	static {
		client.setId(123L);
		client.setIdentification("12345678");
		client.setNames("test names");
		client.setSurnames("test surnames");
	}

	@Test
	public void testGetClientById() {
		when(service.findById(client.getId())).thenReturn(client);

		ResponseEntity<ClientResponseApi> responseEntity = testRest.getForEntity(URL + "/" + client.getId(),
				ClientResponseApi.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(service, times(1)).findById(client.getId());
	}

	@Test
	public void testSave() {

		when(service.save(any(Client.class))).thenReturn(client);

		ClientRequestApi request = new ClientRequestApi();
		request.setIdentification("12345678");
		request.setNames("test names");
		request.setSurnames("test surnames");

		ResponseEntity<ClientResponseApi> responseEntity = testRest.postForEntity(URL, request,
				ClientResponseApi.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		verify(service, times(1)).save(any(Client.class));
	}

	@Test
	public void testUpdate() {

		when(service.update(any(Client.class))).thenReturn(client);

		ClientRequestApi request = new ClientRequestApi();
		request.setIdentification("12345678");
		request.setNames("test names");
		request.setSurnames("test surnames");

		ResponseEntity<ClientResponseApi> responseEntity = testRest.exchange(URL + "/" + client.getId(), HttpMethod.PUT,
				new HttpEntity<>(request), ClientResponseApi.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(service, times(1)).update(any(Client.class));
	}

	@Test
	public void tesDelete() {
		testRest.delete(URL + "/{id}", Collections.singletonMap("id", "1"));
		verify(service, times(1)).deleteById(anyLong());
	}

	@Test
	public void testGetClients() {

		Page<Client> page = new PageImpl<>(Collections.singletonList(client));		
		when(service.findAll(anyInt(), anyInt())).thenReturn(page);

		ResponseEntity<PagedModel> response = testRest.getForEntity(URL, PagedModel.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service, times(1)).findAll(anyInt(), anyInt());
	}

}
