package ar.com.plug.examen.domain.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ConflictResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.Transaction.TransactionStatusEnum;
import ar.com.plug.examen.domain.model.TransactionItem;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceTest {

	@Mock
	private TransactionRepository repository;

	@Mock
	private ProductService productService;

	@Mock
	private ClientService clientService;

	@InjectMocks
	private TransactionServiceImpl service;

	private static final Transaction entity = new Transaction();

	static {
		entity.setId(123L);
		entity.setClient(new Client());
		entity.setCreationDate(new Date());
		entity.setStatus(TransactionStatusEnum.PENDING);
	}

	@Test
	public void testSave() {

		Transaction entity = new Transaction();
		entity.setId(123L);
		Client client = new Client();
		client.setId(123L);
		
		entity.setClient(client);
		entity.setCreationDate(new Date());
		entity.setStatus(TransactionStatusEnum.PENDING);

		when(repository.save(any())).thenReturn(entity);
		when(clientService.findById(anyLong())).thenReturn(client);

		Product product = new Product();
		product.setId(123l);
		product.setStock(10l);
		when(productService.findById(anyLong())).thenReturn(product);

		Set<TransactionItem> items = new HashSet<>();
		TransactionItem item = new TransactionItem();
		item.setProduct(product);
		item.setQuantity(NumberUtils.LONG_ONE);

		items.add(item);
		entity.setItems(items);

		Transaction newEntity = service.save(entity);

		assertNotNull(newEntity);
		assertNotNull(newEntity.getId());
	}

	@Test
	public void testSaveBadResource() {
		Transaction entity = new Transaction();
		entity.setId(123L);
		Client client = new Client();
		client.setId(123L);
		
		entity.setClient(client);
		entity.setCreationDate(new Date());
		entity.setStatus(TransactionStatusEnum.PENDING);

		when(repository.save(any())).thenReturn(entity);
		when(clientService.findById(anyLong())).thenReturn(client);

		Product product = new Product();
		product.setId(123l);
		product.setStock(10l);
		when(productService.findById(anyLong())).thenReturn(product);

		Set<TransactionItem> items = new HashSet<>();
		TransactionItem item = new TransactionItem();
		item.setProduct(product);
		item.setQuantity(NumberUtils.LONG_ONE);

		items.add(item);
		entity.setItems(items);
		
		
		when(repository.save(any())).thenThrow(new RuntimeException());
		assertThatExceptionOfType(BadResourceException.class).isThrownBy(() -> service.save(entity));
	}
	
	@Test
	public void testSaveBadResourceStock() {
		Transaction entity = new Transaction();
		entity.setId(123L);
		Client client = new Client();
		client.setId(123L);
		
		entity.setClient(client);
		entity.setCreationDate(new Date());
		entity.setStatus(TransactionStatusEnum.PENDING);

		when(repository.save(any())).thenReturn(entity);
		when(clientService.findById(anyLong())).thenReturn(client);

		Product product = new Product();
		product.setId(123l);
		product.setStock(NumberUtils.LONG_ZERO);
		when(productService.findById(anyLong())).thenReturn(product);

		Set<TransactionItem> items = new HashSet<>();
		TransactionItem item = new TransactionItem();
		item.setProduct(product);
		item.setQuantity(NumberUtils.LONG_ONE);

		items.add(item);
		entity.setItems(items);
		
		
		when(repository.save(any())).thenThrow(new RuntimeException());
		assertThatExceptionOfType(BadResourceException.class).isThrownBy(() -> service.save(entity));
	}

	@Test
	public void testUpdateStatus() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
		when(repository.save(any())).thenReturn(entity);
		
		Transaction updateEntity = service.updateStatus(123l, TransactionStatusEnum.PENDING);
		assertNull(updateEntity);

		updateEntity = service.updateStatus(123l, TransactionStatusEnum.APPROVED);
		assertNotNull(updateEntity);
		
		when(repository.findById(anyLong())).thenReturn(Optional.of(updateEntity));
		assertThatExceptionOfType(ConflictResourceException.class).isThrownBy(() -> service.updateStatus(123l, TransactionStatusEnum.REJECTED));
	}

	@Test
	public void testFindById() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
		Transaction find = service.findById(123L);
		assertNotNull(find);

	}

	@Test
	public void testFindAll() {
		Page<Transaction> page = mock(Page.class);
		when(repository.findAll(any(Pageable.class))).thenReturn(page);

		Page<Transaction> list = service.findAll(1, 2000);

		assertNotNull(list);
	}
	
	@Test
	public void testFindAllByStatus() {
		Page<Transaction> page = mock(Page.class);
		when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

		Page<Transaction> list = service.findAllByStatus(TransactionStatusEnum.APPROVED, 1, 2000);
		
		assertNotNull(list);
	}

	@Test
	public void testDelete() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
		service.deleteById(123L);
	}

	@Test
	public void testFindByIdBadRequest() {
		assertThatExceptionOfType(BadResourceException.class).isThrownBy(() -> service.findById(null));
	}

	@Test
	public void testFindByIdNotFound() {
		when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> service.findById(123L));
	}

}
