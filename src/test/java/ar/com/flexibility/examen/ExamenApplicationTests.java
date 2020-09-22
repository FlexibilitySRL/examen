package ar.com.flexibility.examen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import ar.com.flexibility.examen.domain.dao.IClientDao;
import ar.com.flexibility.examen.domain.model.Client;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql("/detalles-archivos-h2.sql")
class ExamenApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private IClientDao clientDao;
	
//	@Autowired
//	private IProductDao productDao;
//	
//	@Autowired
//	private IPurchasesTransactionsDao transactionDao;
	
	@Test
	public void testGetClient() {
		Optional<Client> client = clientDao.findById(2L);
		assertEquals(123123123, Integer.parseInt(client.get().getIdentification()));
	}
	
	@Test
	public void testGetAllClients() {
		List<Client> listClient = clientDao.findAll();
		assertEquals(2, listClient.size());
	}
	
//	@Test
//	public void testGetProduct() {
//		Optional<Product> client = productDao.findById(2L);
//		assertEquals(234234324, Integer.parseInt(client.get().getNumberReferences()));
//	}
//	
//	@Test
//	public void testGetPurchasesTransactions() {
//		Optional<PurchasesTransactions> client = transactionDao.findById(1L);
//		assertEquals("pruebas", client.get().getComments());
//	}


}
