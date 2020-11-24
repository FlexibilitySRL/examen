package ar.com.plug.examen.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionDetail;
import ar.com.plug.examen.domain.service.ValidatorsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorsServiceImplTest {

	@Autowired
	ValidatorsService validatorService;
	
	private Transaction transaction;
	private Client client;
	private Seller seller;
	private Product product;
	private TransactionDetail transactionDetail;
	
	@Before
	public void setup() {
		client = new Client();
		seller = new Seller();
		client.setId(1L);
		seller.setId(1L);
		product = new Product(1L, "Product A", 0.75D);
		transactionDetail = new TransactionDetail(null, product, 4);

		transaction = new Transaction.Builder()
				.setId(1L).setClient(client).setSeller(seller)
				.setDate(Calendar.getInstance().getTime())
				.setStatus(StatusEnum.PENDING)
				.setTransactionDetail(transactionDetail).build();
	}
	
	@Test
	public void testValidateTransactionStatus() throws BadRequestException {
		validatorService.validateTransactionStatus(transaction, StatusEnum.REJECTED);
	}
	
	@Test
	public void testValidateTransactionStatus_Null() throws BadRequestException {
		assertThrows(BadRequestException.class, () -> {
			validatorService.validateTransactionStatus(transaction, null);
		});
	}
	
	@Test
	public void testValidateTransactionStatus_SameSatus() throws BadRequestException {
		assertThrows(BadRequestException.class, () -> {
			validatorService.validateTransactionStatus(transaction, StatusEnum.PENDING);
		});
	}
	
	@Test
	public void testCheckCompleteObject() throws BadRequestException {
		boolean valid = validatorService.checkCompleteObject(1L, true);
		assertTrue(valid);
	}
	
	@Test
	public void testCheckCompleteObject_NullObject() throws BadRequestException {
		assertThrows(BadRequestException.class, () -> {
			validatorService.checkCompleteObject(new Client(), false);
		});
	}
}