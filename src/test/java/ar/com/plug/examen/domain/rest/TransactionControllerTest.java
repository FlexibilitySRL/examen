package ar.com.plug.examen.domain.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
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
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.domain.model.TransactionDetailDTO;
import ar.com.plug.examen.domain.service.IClientRepo;
import ar.com.plug.examen.domain.service.ITransactionRepo;
import ar.com.plug.examen.entities.TransactionDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class TransactionControllerTest {
	
	@MockBean
	private ITransactionRepo transactionService;

	@Autowired
	private TestRestTemplate restTemplate;

	private final String URL = "/transaction/";
	
	private TransactionDTO transaction;  
	
	@BeforeAll
	public void settingTransaction() {
		ClientDTO c = new ClientDTO(1L, "lea", "ferreyra", "lean224");
		ProductDTO p = new ProductDTO(2L, "Product 1", 10D, 100);
		TransactionDetailDTO detail = new TransactionDetailDTO(p,10,null);
		List<TransactionDetailDTO> listDetail = new ArrayList<TransactionDetailDTO>();
		listDetail.add(detail);
		transaction = new TransactionDTO(3L, c, listDetail, 1, new Date()); 
	}

	@Test
	public void findAllTransactionsTest() {
		List<TransactionDTO> tlist = Stream.of(transaction).collect(Collectors.toList());
		when(this.transactionService.findAll()).thenReturn(tlist);
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		assertTrue(responseEntity.getBody().size() == 1);
	    verify(this.transactionService, times(1)).findAll();
	}
	
	
	@Test
	public void findTransactionByIDTestReturnsTransactionDTO() throws ResourceNotFoundError {
		when(this.transactionService.getTransactionByID(3L)).thenReturn(transaction);
		ResponseEntity<TransactionDTO> responseEntity = restTemplate.getForEntity(URL + "/3", TransactionDTO.class);
		assertEquals(transaction, responseEntity.getBody());
		verify(this.transactionService, times(1)).getTransactionByID(3L);
	}
	
	@Test
	public void findClientByIDTestReturnsOK() throws ResourceNotFoundError {
		when(this.transactionService.getTransactionByID(3L)).thenReturn(transaction);
		ResponseEntity<TransactionDTO> responseEntity = restTemplate.getForEntity(URL + "/3", TransactionDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(this.transactionService, times(1)).getTransactionByID(3L);
	}
	
	@Test
	public void approveTransactionTest() throws ResourceNotFoundError {
		when(this.transactionService.getTransactionByID(3L)).thenReturn(transaction);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<TransactionDTO> request = new HttpEntity<>(transaction, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "3/approve", HttpMethod.PUT, request, String.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(this.transactionService, times(1)).approveStatus(3L);
	}
	
	@Test
	public void rejectTransactionTest() throws ResourceNotFoundError {
		when(this.transactionService.getTransactionByID(3L)).thenReturn(transaction);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<TransactionDTO> request = new HttpEntity<>(transaction, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "3/reject", HttpMethod.PUT, request, String.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(this.transactionService, times(1)).rejectStatus(3L);
	}
	
	
	
}
