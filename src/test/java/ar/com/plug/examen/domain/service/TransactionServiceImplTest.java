package ar.com.plug.examen.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.PurchaseApprovalApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

	private TransactionServiceImpl transactionService;
	@Mock
	private TransactionRepository transactionRepository;

	@Before
	public void setUp() throws ValidatorException {
		Optional<Transaction> optional = Optional.of(new Transaction(1L,Arrays.asList(new Product()),new Client(1L,"4354353","oscar",LocalDateTime.of(2020, 10, 10, 12, 12)),LocalDateTime.of(2020, 10, 10, 12, 12)));

		Mockito.when(transactionRepository.findById(1L)).thenReturn(optional);

		transactionService = new TransactionServiceImpl(transactionRepository);
	}
	
	@Test
	public void findOk() throws NotExistException {
		TransactionApi transaction = transactionService.find(1L);
		assertThat(transaction.getClient()).
		extracting("getId","getName","getDocument").
		contains(1L,"oscar","4354353");
	}
	@Test
	public void findNull() throws NotExistException {
		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  transactionService.find(2L);
		}).withMessage("La transaccion que quiere consultar no existe");
	}
	@Test
	public void createError() {
		assertThatExceptionOfType(ValidatorException.class)
		  .isThrownBy(() -> {
			  transactionService.create(new PurchaseApprovalApi(Arrays.asList(new ProductApi(1L,"12","")),
					  											new ClientApi(1L,"4354353","oscar")));
		}).withMessage("[[El campo name es obligatorio]]");
		
	}
	@Test
	public void createOk() throws ValidatorException, NotExistException {
		
		transactionService.create(new PurchaseApprovalApi(Arrays.asList(new ProductApi(1L,"12","product")),
														  new ClientApi(1L,"4354353","oscar")));		
	}
	
	@Test
	public void createErrorNull() {
		Mockito.when(transactionRepository.save(Mockito.any())).thenThrow(RuntimeException.class);

		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  transactionService.create(new PurchaseApprovalApi(Arrays.asList(new ProductApi(1L,"12","sdfsd")),
					  											new ClientApi(1L,"4354353","oscar")));
		}).withMessage("Los datos ingresados no son existen en nuestra base de datos");
		
	}

}
