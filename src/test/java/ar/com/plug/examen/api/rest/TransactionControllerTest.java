package ar.com.plug.examen.api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionDetailApi;
import ar.com.plug.examen.app.rest.TransactionController;
import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionDetail;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class TransactionControllerTest {

	@Autowired
	private TransactionController transactionController;
    
	@InjectMocks
	TransactionServiceImpl transactionService;
	
	@Mock
	TransactionRepository transactionRepository;
    
	private TransactionApi transactionApi;
	private ClientApi clientApi;
	private SellerApi sellerApi;
	private ProductApi productApi;
	private TransactionDetailApi transactionDetailApi;
	private TransactionApi filters;
	
	@Before
	public void setup() {
		clientApi = new ClientApi(2L);
		sellerApi = new SellerApi(0L);
		productApi = new ProductApi(1L, "Product A", 0.75D);
		transactionDetailApi = new TransactionDetailApi(null, productApi, 4);

		transactionApi = new TransactionApi.Builder()
				.setClient(clientApi).setSeller(sellerApi)
				.setDate(Calendar.getInstance().getTime())
				.setStatus(StatusEnum.PENDING)
				.setTransactionDetail(transactionDetailApi).build();
		filters = new TransactionApi.Builder()
				.setSeller(new SellerApi(2L))
				.build();
	}

	@Test
	public void testEntity() {
		Transaction entityTest = new Transaction();
		entityTest.setId(0L);
		entityTest.setClient(new Client());
		entityTest.setSeller(new Seller());
		entityTest.setDate(Calendar.getInstance().getTime());
		entityTest.setStatus(StatusEnum.APPROVED);
		entityTest.setTransactionDetail(new ArrayList<TransactionDetail>());
		assertNotNull(entityTest.getId());
		assertNotNull(entityTest.getClient());
		assertNotNull(entityTest.getSeller());
		assertNotNull(entityTest.getDate());
		assertNotNull(entityTest.getStatus());
		assertNotNull(entityTest.getTransactionDetail());
		assertNotNull(entityTest.toString());
	}

	@Test
	public void testApi() {
		TransactionApi apiTest = new TransactionApi();
		apiTest.setId(0L);
		apiTest.setClient(new ClientApi());
		apiTest.setSeller(new SellerApi());
		apiTest.setDate(Calendar.getInstance().getTime());
		apiTest.setStatus(StatusEnum.APPROVED);
		apiTest.setTransactionDetail(new ArrayList<TransactionDetailApi>());
		assertNotNull(apiTest.getId());
		assertNotNull(apiTest.getClient());
		assertNotNull(apiTest.getSeller());
		assertNotNull(apiTest.getDate());
		assertNotNull(apiTest.getStatus());
		assertNotNull(apiTest.getTransactionDetail());
		assertNotNull(apiTest.toString());
	}

	@Test
	public void testListAll() {
		List<TransactionApi> all = transactionController.listTransactions().getBody();
		assertFalse(all.isEmpty());
	}
	
	@Test
	public void findByFilters() {
		List<TransactionApi> all = transactionController.findByFilters(filters).getBody();
		assertFalse(all.isEmpty());
	}

	@Test
	@Transactional
	public void testSave() throws BadRequestException, NotFoundException {
		clientApi = new ClientApi(1L);
		sellerApi = new SellerApi(1L);
		productApi = new ProductApi(1L, "Product A", 0.75D);
		transactionDetailApi = new TransactionDetailApi(null, productApi, 4);

		transactionApi = new TransactionApi.Builder()
				.setClient(clientApi).setSeller(sellerApi)
				.setDate(Calendar.getInstance().getTime())
				.setStatus(StatusEnum.PENDING)
				.setTransactionDetail(transactionDetailApi).build();
		TransactionApi saved = transactionController.save(transactionApi).getBody();
		assertNotNull(saved);
		assertNotNull(saved.getId());
	}

	@Test
	@Transactional
	public void testDeleteById() throws NotFoundException {
		List<TransactionApi> transactionsApi = transactionController.findByFilters(filters).getBody();
		for (TransactionApi transaction: transactionsApi) {
			HttpStatus code = transactionController.deleteById(transaction.getId()).getStatusCode();
			assertEquals(HttpStatus.NO_CONTENT, code);
		}
		transactionsApi = transactionController.findByFilters(filters).getBody();
		assertTrue(transactionsApi.isEmpty());
	}

	@Test
	@Transactional
	public void testUpdateTransactionStatusById() throws NotFoundException, BadRequestException  {
		TransactionApi updated = transactionController.updateTransactionStatusById(1L, StatusEnum.REJECTED).getBody();
		assertNotNull(updated);
		assertNotNull(updated.getId());
		assertEquals(StatusEnum.REJECTED, updated.getStatus());
		assertNotEquals(transactionApi.getStatus(), updated.getStatus());
	}

	@Test
	public void testTotalAmountByTransactionId() throws NotFoundException {
		Double total = transactionController.totalAmountByTransactionId(1L).getBody();
		assertNotNull(total);
		assertEquals(82.5D, total);
	}
}