package ar.com.plug.examen.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionDetailApi;
import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionDetail;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.service.ValidatorsService;

public class TransactionServiceImplTest {

	@InjectMocks
	TransactionServiceImpl transactionService;

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	ProductRepository productRepository;

	@Mock
	SellerService sellerService;

	@Mock
	ClientService clientService;

	@Mock
	ConverterService converter;

	@Mock
	ValidatorsService validators;

	StatusEnum status;
	private Transaction transaction;
	private TransactionApi transactionApi;
	private Client client;
	private ClientApi clientApi;
	private Seller seller;
	private SellerApi sellerApi;
	private Product product;
	private ProductApi productApi;
	private TransactionDetail transactionDetail;
	private TransactionDetailApi transactionDetailApi;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		client = new Client(1L, "John Doe");
		clientApi = new ClientApi(1L, "John Doe");
		seller = new Seller(1L, "John Doe");
		sellerApi = new SellerApi(1L, "John Doe");
		product = new Product(1L, "Product A", 8.25D);
		productApi = new ProductApi(1L, "Product A", 8.25D);
		transactionDetail = new TransactionDetail(1L, product, 95);
		transactionDetailApi = new TransactionDetailApi(1L, productApi, 95);

		transaction = new Transaction.Builder().setId(1L).setClient(client).setSeller(seller)
				.setTransactionDetail(transactionDetail).setDate(Calendar.getInstance().getTime())
				.setStatus(StatusEnum.PENDING).build();

		transactionApi = new TransactionApi.Builder().setId(1L).setClient(clientApi).setSeller(sellerApi)
				.setTransactionDetail(transactionDetailApi).build();
	}

	@Test
	void testListAll() {
		when(converter.convertList(transactionRepository.findAll(), TransactionApi.class))
				.thenReturn(Arrays.asList(transactionApi));
		List<TransactionApi> found = transactionService.listAll();
		assertNotNull(found);
	}

	@Test
	void testFindByFilters() {
		when(converter.convertList(transactionRepository.findAll(), TransactionApi.class))
				.thenReturn(Arrays.asList(transactionApi));
		List<TransactionApi> found = transactionService.findByFilters(transactionApi);
		assertNotNull(found);
	}

	@Test
	void testSave() throws BadRequestException, NotFoundException {
		when(converter.convert(clientService.findById(anyLong()), Client.class)).thenReturn(client);
		when(converter.convert(sellerService.findById(anyLong()), Seller.class)).thenReturn(seller);
		when(converter.convertList(transaction.getTransactionDetail(), TransactionDetail.class))
				.thenReturn(Arrays.asList(transactionDetail));
		when(productRepository.findAllById(Arrays.asList(anyLong()))).thenReturn(Arrays.asList(product));
		when(transactionRepository.save(converter.convert(transactionApi, Transaction.class))).thenReturn(transaction);
		when(converter.convert(transaction, TransactionApi.class)).thenReturn(transactionApi);
		TransactionApi saved = transactionService.save(transactionApi);
		assertNotNull(saved);
		assertNotNull(saved.getId());
	}

	@Test
	void testSave_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(transactionApi, true)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			transactionService.save(transactionApi);
		});
	}

	@Test
	void testSave_ClientNotFoundException() throws NotFoundException {
		when(clientService.findById(anyLong())).thenThrow(NotFoundException.class);
		assertThrows(NotFoundException.class, () -> {
			transactionService.save(transactionApi);
		});
	}

	@Test
	void testSave_SellerNotFoundException() throws NotFoundException {
		when(sellerService.findById(anyLong())).thenThrow(NotFoundException.class);
		assertThrows(NotFoundException.class, () -> {
			transactionService.save(transactionApi);
		});
	}

	@Test
	void testDeleteById() throws NotFoundException, BadRequestException {
		when(transactionRepository.existsById(anyLong())).thenReturn(true);
		transactionService.deleteById(1L);
	}

	@Test
	void testDeleteById_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(transaction.getId(), false)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			transactionService.deleteById(transactionApi.getId());
		});
	}

	@Test
	void testDeleteById_NotFoundException() throws NotFoundException {
		when(transactionRepository.existsById(transaction.getId())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> {
			transactionService.deleteById(transactionApi.getId());
		});
	}

	@Test
	void updateTransactionStatusById() throws NotFoundException, BadRequestException {
		when(transactionRepository.findOneById(anyLong())).thenReturn(transaction);
		when(transactionRepository.save(converter.convert(transaction, Transaction.class))).thenReturn(transaction);
		when(converter.convert(transaction, TransactionApi.class)).thenReturn(transactionApi);
		TransactionApi updated = transactionService.updateTransactionStatusById(transaction.getId(),
				StatusEnum.APPROVED);
		assertEquals(updated.getId(), transaction.getId());
		assertNotEquals(updated.getStatus(), transaction.getStatus());
	}

	@Test
	void updateTransactionStatusById_NotFoundException() throws NotFoundException {
		when(transactionRepository.findOneById(anyLong())).thenReturn(null);
		assertThrows(NotFoundException.class, () -> {
			transactionService.updateTransactionStatusById(transaction.getId(), StatusEnum.APPROVED);
		});
	}

	@Test
	public void testTotalAmountByTransactionId() throws BadRequestException, NotFoundException {
		when(transactionRepository.findOneById(anyLong())).thenReturn(transaction);
		Double total = transactionService.totalAmountByTransactionId(transaction.getId());
		assertNotNull(total);
		assertEquals(total, 783.75D);
	}

	@Test
	void testTotalAmountByTransactionId_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(transaction.getId(), false)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			transactionService.deleteById(transactionApi.getId());
		});
	}

	@Test
	void testTotalAmountByTransactionId_NotFoundException() throws NotFoundException {
		when(transactionRepository.findOneById(anyLong())).thenReturn(null);
		assertThrows(NotFoundException.class, () -> {
			transactionService.deleteById(transactionApi.getId());
		});
	}
}