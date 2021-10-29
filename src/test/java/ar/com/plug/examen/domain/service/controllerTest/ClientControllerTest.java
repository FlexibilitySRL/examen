package ar.com.plug.examen.domain.service.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.rest.ClientController;
import ar.com.plug.examen.creator.ClientCreator;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.dto.ClientDto;
import ar.com.plug.examen.dto.ProductDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ClientControllerTest {

	@InjectMocks
	private ClientController controller;
	
	@MockBean
	private ClientServiceImpl service;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private final String url = "/client/";
	
	
	@Test
	public void createClientTest() {
		ClientDto client = ClientCreator.createClientDtoMock();
		
		ResponseEntity<ClientDto> response = restTemplate.postForEntity(url + "create", client, ClientDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.service, times(1)).save(any(ClientDto.class));
		
	}
	
	@Test
	public void findAllClientTest() {
		List<ClientDto> cList = ClientCreator
				.createClientDtoList(ClientCreator.createClientDtoWithId(1L),
						ClientCreator.createClientDtoWithId(2L));
		
		when(service.findAll()).thenReturn(cList);

		ResponseEntity<List> response = restTemplate.getForEntity(url + "all", List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().size());
		
		verify(this.service, times(1)).findAll();
		
	}
	@Test
	public void findClientByIdTest() throws Exception {
		ClientDto client = ClientCreator.createClientDtoMock();
		
		when(service.findById(1L)).thenReturn(client);

		ResponseEntity<ProductDto> response = restTemplate.getForEntity(url + 1L, ProductDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.service, times(1)).findById(1L);
		
	}
	
	
	@Test
	public void upgradeClientTest() throws Exception {
		ClientDto client = ClientCreator.createClientDtoMock();
		ClientDto cMock = ClientCreator.createClientDtoMock();
		
		when(service.update(client)).thenReturn(cMock);
		
		HttpEntity<ClientDto> httpEntity = new HttpEntity<>(cMock);

		ResponseEntity<ClientDto> response = restTemplate.exchange(url + "update", HttpMethod.PUT, httpEntity, ClientDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.service, times(1)).update(any());
		
	}
	

	@Test
	public void deleteClientTest() {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "1");
	    restTemplate.delete(url + "delete/{id}", params);

	    verify(this.service, times(1)).delete(1L);
	}
	
}
