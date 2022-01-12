package ar.com.plug.examen.app.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.api.transaction.RequestId;
import ar.com.plug.examen.app.api.transaction.TransactionRequestApi;
import ar.com.plug.examen.app.api.transaction.TransactionResponseApi;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.Transaction.TransactionStatusEnum;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class TransactionControllerTest {

	private final String URL = "/transactions";

	@InjectMocks
	private TransactionController controller;

	@Autowired
	private TestRestTemplate testRest;

	@MockBean
	private TransactionServiceImpl service;

	private static final Transaction entity = new Transaction();

	static {
		entity.setId(123L);
		entity.setClient(new Client());
		entity.setCreationDate(new Date());
		entity.setStatus(TransactionStatusEnum.PENDING);
	}

	@Test
	public void testGetById() {
		when(service.findById(entity.getId())).thenReturn(entity);

		ResponseEntity<TransactionResponseApi> responseEntity = testRest.getForEntity(URL + "/" + entity.getId(),
				TransactionResponseApi.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(service, times(1)).findById(entity.getId());
	}

	@Test
	public void testSave() {

		when(service.save(any(Transaction.class))).thenReturn(entity);

		TransactionRequestApi request = new TransactionRequestApi();
		RequestId clientId = new RequestId();
		request.setClient(clientId);

		ResponseEntity<TransactionResponseApi> responseEntity = testRest.postForEntity(URL, request,
				TransactionResponseApi.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		verify(service, times(1)).save(any(Transaction.class));
	}

	@Test
	public void tesDelete() {
		testRest.delete(URL + "/{id}", Collections.singletonMap("id", "1"));
		verify(service, times(1)).deleteById(anyLong());
	}

	@Test
	public void testGetAll() {

		Page<Transaction> page = new PageImpl<>(Collections.singletonList(entity));
		when(service.findAll(anyInt(), anyInt())).thenReturn(page);

		ResponseEntity<PagedModel> response = testRest.getForEntity(URL, PagedModel.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service, times(1)).findAll(anyInt(), anyInt());
	}

	@Test
	public void testGetAllByStatus() {

		Page<Transaction> page = new PageImpl<>(Collections.singletonList(entity));
		when(service.findAllByStatus(any(TransactionStatusEnum.class), anyInt(), anyInt())).thenReturn(page);

		ResponseEntity<PagedModel> response = testRest.getForEntity(URL + "?status=APPROVED", PagedModel.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service, times(1)).findAllByStatus(any(TransactionStatusEnum.class), anyInt(), anyInt());
	}

}
