package ar.com.plug.examen.application;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.TestUtil;
import ar.com.plug.examen.domain.Transaction;
import ar.com.plug.examen.infrastructure.db.repository.ClientEntityRepository;
import ar.com.plug.examen.infrastructure.db.repository.ProductEntityRepository;
import ar.com.plug.examen.infrastructure.db.repository.TransactionEntityRepository;
import ar.com.plug.examen.shared.config.MenssageResponse;
import ar.com.plug.examen.shared.exception.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class TransactionsServiceImplTest {
	@Mock
	private ClientEntityRepository clientEntityRepository;
	@Mock
	private ProductEntityRepository productEntityRepository;

	@Mock
	private TransactionEntityRepository transactionEntityRepository;

	@Mock
	private MenssageResponse menssageResponse;
	@InjectMocks
	private TransactionsServiceImpl transactionsServiceImpl;

	@Before
	public void setup() {
		when(menssageResponse.getMessages()).thenReturn(TestUtil.buidlMenssageResponse());
	}

	@Test
	public void createTransactionTestOk() {
		int nProduct = 2;
		Double total = 3 * TestUtil.PRICE_PRODUCT;
		when(clientEntityRepository.findById(TestUtil.ID_1))
				.thenReturn(Optional.of(TestUtil.buidlClientEntity(TestUtil.ID_1)));
		when(productEntityRepository.findById(TestUtil.ID_1))
				.thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_1));
		when(productEntityRepository.findById(TestUtil.ID_2))
				.thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_2));
		when(productEntityRepository.saveAll(any())).thenReturn(TestUtil.buidlProductEntityList(nProduct));
		when(clientEntityRepository.save(any())).thenReturn(TestUtil.buidlClientEntity(TestUtil.ID_1));

		Transaction transaccion = transactionsServiceImpl
				.createTransaction(TestUtil.buildTransaction(TestUtil.ID_1, nProduct));
		assertEquals(transaccion.getItems().size(), nProduct);
		assertEquals(transaccion.getTotal(), total);
	}

	@Test(expected = NotFoundException.class)
	public void createTransactionTestProductNotFoundException() {
		int nProduct = 2;
		Double total = 3 * TestUtil.PRICE_PRODUCT;
		when(clientEntityRepository.findById(TestUtil.ID_1))
				.thenReturn(Optional.of(TestUtil.buidlClientEntity(TestUtil.ID_1)));
		when(productEntityRepository.findById(TestUtil.ID_1))
				.thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_1));

		Transaction transaccion = transactionsServiceImpl
				.createTransaction(TestUtil.buildTransaction(TestUtil.ID_1, nProduct));
		assertEquals(transaccion.getItems().size(), nProduct);
		assertEquals(transaccion.getTotal(), total);
	}

	@Test(expected = NotFoundException.class)
	public void createTransactionTestClientNotFoundException() {
		int nProduct = 1;
		Double total = 1 * TestUtil.PRICE_PRODUCT;
		Transaction transaccion = transactionsServiceImpl
				.createTransaction(TestUtil.buildTransaction(TestUtil.ID_2, nProduct));
		assertEquals(transaccion.getItems().size(), nProduct);
		assertEquals(transaccion.getTotal(), total);
	}

	@Test
	public void findByClientEmailOK() {
		when(clientEntityRepository.findByEmail(TestUtil.EMAIL_1))
				.thenReturn(Optional.of(TestUtil.buidlClientEntity(TestUtil.ID_1)));
		List<Transaction> transaccions = transactionsServiceImpl
				.findByClientEmail(TestUtil.EMAIL_1, true);
		assertEquals(transaccions.size(), 1);
	}

	@Test(expected = NotFoundException.class)
	public void findByClientEmailClientNotFoundException() {
		transactionsServiceImpl
				.findByClientEmail(TestUtil.EMAIL_2, true);
	}

	@Test
	public void findByApprovedOK() {
		int nProduct = 2;
		when(transactionEntityRepository.findByApproved(true))
				.thenReturn(TestUtil.buildTransactionEntites(TestUtil.ID_1, nProduct, false));
		List<Transaction> transaccions = transactionsServiceImpl
				.findByApproved(true);
		assertEquals(nProduct, transaccions.size());
	}

	@Test
	public void approvedTransacctionsOK() {
		List<String> ids = Arrays.asList("1", "2", "3");
		String[] responseStatus = { TransactionsServiceImpl.APPROVED, TransactionsServiceImpl.APPROVED,
				TransactionsServiceImpl.NOT_FOUND };
		int nProduct = 2;
		when(transactionEntityRepository.findAllById(ids))
				.thenReturn(TestUtil.buildTransactionEntites(TestUtil.ID_1, nProduct, false));
		Map<String, String> response = transactionsServiceImpl.approvedTransacctions(ids);

		assertArrayEquals(responseStatus, response.values().toArray());
	}
}
