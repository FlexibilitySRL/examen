package ar.com.flexibility.examen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import ar.com.flexibility.examen.domain.dao.IProductDao;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql("/detalles-archivos-h-b.sql")
public class TestAppProductAndPurchase {
	
	@Test
	void contextLoads() {
	}

	@Autowired
	private IProductDao productDao;
	
//	@Test
//	public void testGetProduct() {
//		List<Product> product = productDao.findAll();
//		assertEquals(2, product.size());
//	}

//	@Test	
//	public void testGetPurchasesTransactions() {
//		Optional<PurchasesTransactions> client = transactionDao.findById(1L);
//		assertEquals("pruebas", client.get().getComments());
//	}

}
