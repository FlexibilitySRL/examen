package ar.com.plug.examen.domain.service.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(scripts = {"/schema.sql"})
public class PurchaseRepositoryTest
{
	@Autowired
	private PurchaseRepository repository;

	@Autowired
	private ClientRepository clientRepository;

	private Purchase purchase1;
	private Purchase purchase2;
	private Purchase purchase3;
	private Purchase purchase4;
	private Purchase purchase5;
	private Purchase purchase6;

	private Client client1;

	@Before
	public void setup() {
		client1 = Client.builder()
			.name("TestClient1")
			.lastname("TestClient1Lastname")
			.document("564631")
			.phone("+595971100100")
			.email("testclient1@email.com")
			.active(Boolean.TRUE)
			.build();

		purchase1 = Purchase.builder()
			.receiptNumber("random-value-1")
			.total(new BigDecimal(100))
			.approved(Boolean.TRUE)
			.build();

		purchase2 = Purchase.builder()
			.receiptNumber("random-value-2")
			.total(new BigDecimal(200))
			.approved(Boolean.TRUE)
			.build();

		purchase3 = Purchase.builder()
			.receiptNumber("random-value-3")
			.total(new BigDecimal(300))
			.approved(Boolean.TRUE)
			.build();

		purchase4 = Purchase.builder()
			.receiptNumber("random-value-4")
			.total(new BigDecimal(400))
			.approved(Boolean.TRUE)
			.build();

		purchase5 = Purchase.builder()
			.receiptNumber("random-value-5")
			.total(new BigDecimal(500))
			.approved(Boolean.TRUE)
			.build();

		purchase6 = Purchase.builder()
			.receiptNumber("random-value-6")
			.total(new BigDecimal(600))
			.approved(Boolean.TRUE)
			.build();
	}

	@Test
	public void autowiredNotNull()
	{
		assertThat(repository).isNotNull();
		assertThat(clientRepository).isNotNull();
	}

	@Test
	public void testSavePurchase()
	{
		Client savedClient = clientRepository.save(client1);
		purchase1.setClient(savedClient);
		Purchase savedEntity = repository.save(purchase1);
		assertThat(savedEntity.getId()).isGreaterThan(0);
		assertThat(savedEntity.getApproved()).isEqualTo(Boolean.TRUE);
		assertThat(savedEntity.getTotal()).isEqualTo(new BigDecimal(100));
		assertThat(savedEntity.getClient().getId()).isEqualTo(savedClient.getId());
	}

	@Test
	public void testFindPurchaseByReceiptNumber()
	{
		Client savedClient = clientRepository.save(client1);
		purchase1.setClient(savedClient);
		Purchase savedEntity = repository.save(purchase1);
		assertThat(savedEntity).isNotNull();
		assertThat(savedEntity.getId()).isEqualTo(repository.findByReceiptNumber(savedEntity.getReceiptNumber()).getId());
	}

	@Test
	public void testGetPurchasesPageable()
	{
		Client savedClient = clientRepository.save(client1);

		purchase1.setClient(savedClient);
		repository.save(purchase1);

		purchase2.setClient(savedClient);
		repository.save(purchase2);

		purchase3.setClient(savedClient);
		repository.save(purchase3);

		purchase4.setClient(savedClient);
		repository.save(purchase4);

		purchase5.setClient(savedClient);
		repository.save(purchase5);

		Page<Purchase> page1 = repository.findAll(PageRequest.of(0, 3));
		assertThat(page1).isNotNull();
		assertThat(page1.getSize()).isEqualTo(3);
		assertThat(page1.getContent().get(0).getReceiptNumber()).isEqualTo(purchase1.getReceiptNumber());
		assertThat(page1.getContent().get(1).getReceiptNumber()).isEqualTo(purchase2.getReceiptNumber());

		Page<Purchase> page2 = repository.findAll(PageRequest.of(1, 3));
		assertThat(page2).isNotNull();
		assertThat(page2.getSize()).isEqualTo(3);
		assertThat(page2.getContent().get(0).getReceiptNumber()).isEqualTo(purchase4.getReceiptNumber());
		assertThat(page2.getContent().get(1).getReceiptNumber()).isEqualTo(purchase5.getReceiptNumber());
	}

}
