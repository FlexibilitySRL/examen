package ar.com.plug.examen.domain.repository.specification;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionSpecificationTest {

	@Test
	public void testSpecificationClientId() {
		ClientApi filter = new ClientApi(1L);
		Specification<Transaction> spec = TransactionSpecification.especificacionClientId(filter);
		assertNotNull(spec);
	}

	@Test
	public void testSpecificationClientName() {
		ClientApi filter = new ClientApi("A name");
		Specification<Transaction> spec = TransactionSpecification.especificacionClientName(filter);
		assertNotNull(spec);
	}

	@Test
	public void testSpecificationSellerId() {
		SellerApi filter = new SellerApi(1L);
		Specification<Transaction> spec = TransactionSpecification.especificacionSellerId(filter);
		assertNotNull(spec);
	}

	@Test
	public void testSpecificationSellerName() {
		SellerApi filter = new SellerApi("A name");
		Specification<Transaction> spec = TransactionSpecification.especificacionSellerName(filter);
		assertNotNull(spec);
	}

	@Test
	public void testSpecificationStatus() {
		Specification<Transaction> spec = TransactionSpecification.especificacionStatus(StatusEnum.APPROVED);
		assertNotNull(spec);
	}

	@Test
	public void testSpecificationDate() {
		Date aDate = Calendar.getInstance().getTime();
		Specification<Transaction> spec = TransactionSpecification.especificacionDate(aDate);
		assertNotNull(spec);
	}
}