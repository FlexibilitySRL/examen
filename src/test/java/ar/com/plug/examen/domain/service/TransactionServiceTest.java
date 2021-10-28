package ar.com.plug.examen.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.creator.ClientCreator;
import ar.com.plug.examen.creator.ProductCreator;
import ar.com.plug.examen.creator.TransactionCreator;
import ar.com.plug.examen.dao.entities.Client;
import ar.com.plug.examen.dao.entities.Product;
import ar.com.plug.examen.dao.entities.Transaction;
import ar.com.plug.examen.dao.jpa.TransactionJpaDao;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;
import ar.com.plug.examen.dto.TransactionDto;
import ar.com.plug.examen.mapper.TransactionMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceTest {

	
	@InjectMocks
	private TransactionServiceImpl service;
	
	@Mock
	private TransactionMapper mapper;
	
	@Mock
	private TransactionJpaDao jpa;
	
	
	@Test
	public void createTransactionsTest() {
		Client clientMock = ClientCreator.createClientWithId((long)1);
		List<Product> lProducts = ProductCreator.createProductList(ProductCreator.createProductWithId((long)1),
				ProductCreator.createProductWithId((long)2));
		TransactionDto tMock = TransactionCreator.createTransactionDtoWithId((long)1);
		Transaction transaction = TransactionCreator.createTransaction(clientMock, lProducts);
		TransactionDto tToSave = TransactionCreator.createTransactionDto(clientMock, lProducts);
		
		when(this.mapper.entityToDto(this.jpa.save(transaction))).thenReturn(tMock);
		
		TransactionDto response = service.save(tToSave);
		assertEquals(tMock, response);
		verify(this.jpa, times(1)).save(transaction);
	}
	
	@Test
	public void findAllTransactionsTest() {
		Client clientMock1 = ClientCreator.createClientWithId((long)1);
		Client clientMock2 = ClientCreator.createClientWithId((long)2);
		List<Product> lProducts = ProductCreator.createProductList(ProductCreator.createProductWithId((long)1),
				ProductCreator.createProductWithId((long)2));
		List<TransactionDto> tTransaction = TransactionCreator.createTransactionDtoList(
				TransactionCreator.createTransactionDto(clientMock1, lProducts),
				TransactionCreator.createTransactionDto(clientMock2, lProducts));
		
		when(this.mapper.entityListToDtoList(this.jpa.findAll())).thenReturn(tTransaction);
		
		List<TransactionDto> response = service.findAll();
		assertEquals(tTransaction, response);
		verify(this.jpa, times(2)).findAll();
	}
	

	@Test
	public void findTransactionsByIdTest() throws Exception {
		Transaction t = TransactionCreator.createTransactionWithId((long)1);
		
		when(this.jpa.findById((long)1)).thenReturn(Optional.of(t));
		
		TransactionDto response = service.findById((long)1);
		assertEquals(mapper.entityToDto(t), response);
		verify(this.jpa, times(1)).findById((long)1);
	}
}
