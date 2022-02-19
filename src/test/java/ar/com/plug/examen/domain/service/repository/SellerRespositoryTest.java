package ar.com.plug.examen.domain.service.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
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
public class SellerRespositoryTest
{
	@Autowired
	private SellerRepository repository;

	private Seller seller1;
	private Seller seller2;
	private Seller seller3;
	private Seller seller4;
	private Seller seller5;
	private Seller seller6;

	@Before
	public void setup()
	{
		seller1 = Seller.builder()
			.code("SELL-1")
			.document("0-11")
			.description("SELLERTEST1")
			.active(Boolean.TRUE)
			.build();

		seller2 = Seller.builder()
			.code("SELL-2")
			.document("0-22")
			.description("SELLERTEST2")
			.active(Boolean.TRUE)
			.build();

		seller3 = Seller.builder()
			.code("SELL-3")
			.document("0-33")
			.description("SELLERTEST3")
			.active(Boolean.TRUE)
			.build();

		seller4 = Seller.builder()
			.code("SELL-4")
			.document("0-44")
			.description("SELLERTEST4")
			.active(Boolean.TRUE)
			.build();

		seller5 = Seller.builder()
			.code("SELL-5")
			.document("0-55")
			.description("SELLERTEST5")
			.active(Boolean.TRUE)
			.build();

		seller6 = Seller.builder()
			.code("SELL-6")
			.document("0-66")
			.description("SELLERTEST6")
			.active(Boolean.TRUE)
			.build();
	}

	@Test
	public void autowiredNotNull()
	{
		assertThat(repository).isNotNull();
	}

	@Test
	public void testSaveSeller()
	{
		Seller savedEntity = repository.save(seller1);
		assertThat(savedEntity.getId()).isGreaterThan(0);
		assertThat(savedEntity.getActive()).isEqualTo(Boolean.TRUE);
		assertThat(savedEntity.getCode()).isEqualTo("SELL-1");
		assertThat(savedEntity.getDescription()).isEqualTo("SELLERTEST1");
		assertThat(savedEntity.getDocument()).isEqualTo("0-11");
	}

	@Test
	public void testFindSellerByCode()
	{
		Seller savedEntity = repository.save(seller2);
		assertThat(savedEntity).isNotNull();
		assertThat(savedEntity.getCode()).isEqualTo(repository.findByCode("SELL-2").getCode());
	}

	@Test
	public void testGetSellersPageable()
	{
		repository.save(seller1);
		repository.save(seller2);
		repository.save(seller3);
		repository.save(seller4);
		repository.save(seller5);

		Page<Seller> page1 = repository.findAll(PageRequest.of(0, 3));
		assertThat(page1).isNotNull();
		assertThat(page1.getSize()).isEqualTo(3);
		assertThat(page1.getContent().get(0).getCode()).isEqualTo("SELL-1");
		assertThat(page1.getContent().get(1).getCode()).isEqualTo("SELL-2");

		Page<Seller> page2 = repository.findAll(PageRequest.of(1, 3));
		assertThat(page2).isNotNull();
		assertThat(page2.getSize()).isEqualTo(3);
		assertThat(page2.getContent().get(0).getCode()).isEqualTo("SELL-4");
		assertThat(page2.getContent().get(1).getCode()).isEqualTo("SELL-5");
	}
}
