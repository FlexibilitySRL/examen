package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.controller.TransactionController;
import ar.com.plug.examen.creator.ClientCreator;
import ar.com.plug.examen.creator.TransactionCreator;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;
import ar.com.plug.examen.dto.ClientDto;
import ar.com.plug.examen.dto.ProductDto;
import ar.com.plug.examen.dto.TransactionDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class TransactionControllerTest {
	
	@InjectMocks
	private TransactionController controller;
	
	@MockBean
	private TransactionServiceImpl service;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private final String url = "/transaction/";
	
	@Test
	public void createTransactionTest() {
		TransactionDto transaction = TransactionCreator.createTransactionDtoMock();
		
		ResponseEntity<TransactionDto> response = restTemplate.postForEntity(url + "create", transaction, TransactionDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.service, times(1)).save(any(TransactionDto.class));
		
	}
	
	@Test
	public void findAllTransactionTest() {
		List<TransactionDto> tList = TransactionCreator
				.createTransactionDtoList(TransactionCreator.createTransactionDtoMock(),
						TransactionCreator.createTransactionDtoMock());
		
		when(service.findAll()).thenReturn(tList);

		ResponseEntity<List> response = restTemplate.getForEntity(url + "all", List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().size());
		
		verify(this.service, times(1)).findAll();
		
	}
	@Test
	public void findTransactionByIdTest() throws Exception {
		TransactionDto transaction = TransactionCreator.createTransactionDtoMock();
		
		when(service.findById(1L)).thenReturn(transaction);

		ResponseEntity<TransactionDto> response = restTemplate.getForEntity(url + 1L, TransactionDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.service, times(1)).findById(1L);
		
	}
	
}
