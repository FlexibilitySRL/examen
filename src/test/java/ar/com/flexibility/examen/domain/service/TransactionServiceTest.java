package ar.com.flexibility.examen.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.com.flexibility.examen.app.rest.dto.TransactionRequestDto;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.TransactionRepository;
import ar.com.flexibility.examen.domain.service.impl.TransactionServiceImpl;
import ar.com.flexibility.examen.exception.DataValidationException;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;

	@Mock
	private TransactionRepository transactionRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
    public void findTransaction() {
    	Transaction transaction = new Transaction();
    	transaction.setId(1);
    	Optional<Transaction> optResult = Optional.of(transaction);
    	when(transactionRepository.findById(Mockito.anyInt())).thenReturn(optResult);
    	
    	Transaction result = transactionServiceImpl.findTransaction(1);
    	Assertions.assertEquals(transaction, result);
    }
	
	@Test
    public void approveTransaction_throwsException_WhenTransactionNotExist() {
		TransactionRequestDto dto = new TransactionRequestDto();
    	dto.setApproved(1);

    	dto.setTransactionId(1);
    	
    	when(transactionRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    	
    	DataValidationException exception = assertThrows(DataValidationException.class, () ->  transactionServiceImpl.approveTransaction(dto));
    	Assertions.assertNotNull(exception.getMessage());
    }
	
	@Test
	public void createTransaction_throwsException_WhenProductNotExist() {
		TransactionRequestDto dto = new TransactionRequestDto();
    	dto.setCustomerId(1);
    	dto.setProductId(1);
    	
    	when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Customer()));
    	when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    	
    	DataValidationException exception = assertThrows(DataValidationException.class, () ->  transactionServiceImpl.createTransaction(dto));
    	Assertions.assertNotNull(exception.getMessage());
	}
}
