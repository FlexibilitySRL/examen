package ar.com.plug.examen.domain.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
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

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.SellerDTO;
import ar.com.plug.examen.domain.service.IClientRepo;
import ar.com.plug.examen.domain.service.ISellerRepo;
import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Seller;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class SellerControllerTest {

	@MockBean
	private ISellerRepo sellerService;

	@Autowired
	private TestRestTemplate restTemplate;

	private final String URL = "/sellers/";
	
	
	
	@Test
	public void saveSellerTest() throws Exception {
		Seller s = new Seller(1L, "Seller", null);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Seller> request = new HttpEntity<>(s, headers);
		ResponseEntity<SellerDTO> response = restTemplate.postForEntity(URL, request, SellerDTO.class);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	public void findAllSellerTest() {
		List<SellerDTO> sellers = Stream.of(new SellerDTO(1L, "Seller")).collect(Collectors.toList());
		when(this.sellerService.findAll()).thenReturn(sellers);
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		assertTrue(responseEntity.getBody().size() == 1);
	    verify(this.sellerService, times(1)).findAll();
	}

	@Test
	public void deleteSellerTest() throws ResourceNotFoundError {
		restTemplate.delete(URL + "1");
		verify(this.sellerService, times(1)).delete(1L);
	}

	@Test
	public void findSellerByIDTestReturnsSellerDTO() throws ResourceNotFoundError {
		SellerDTO s = new SellerDTO(1L, "Seller");
		when(this.sellerService.findSellerByID(1L)).thenReturn(s);
		ResponseEntity<SellerDTO> responseEntity = restTemplate.getForEntity(URL + "/1", SellerDTO.class);
		assertEquals(s, responseEntity.getBody());
		verify(this.sellerService, times(1)).findSellerByID(1L);
	}

	@Test
	public void findSellerByIDTestReturnsOK() throws ResourceNotFoundError {
		SellerDTO s = new SellerDTO(1L, "Seller");
		when(this.sellerService.findSellerByID(1L)).thenReturn(s);
		ResponseEntity<SellerDTO> responseEntity = restTemplate.getForEntity(URL + "/1", SellerDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void updateSellerDTOReturnsOK() throws ResourceNotFoundError, BadRequestError {
		SellerDTO s = new SellerDTO(1L, "Seller");
		when(this.sellerService.update(any())).thenReturn(s);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<SellerDTO> request = new HttpEntity<>(s, headers);
		ResponseEntity<SellerDTO> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, request, SellerDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(this.sellerService, times(1)).update(s);
	}
	
	
}
