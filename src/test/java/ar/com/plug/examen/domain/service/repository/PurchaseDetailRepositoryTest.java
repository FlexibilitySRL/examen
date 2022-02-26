package ar.com.plug.examen.domain.service.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.util.List;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseDetail;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseDetailRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
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
public class PurchaseDetailRepositoryTest
{
	@Autowired
	private PurchaseDetailRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private ClientRepository clientRepository;

	private Client client1;
	private Seller seller1;
	private Product product1;
	private Purchase purchase1;

	private PurchaseDetail purchaseDetail1;
	private PurchaseDetail purchaseDetail2;
	private PurchaseDetail purchaseDetail3;
	private PurchaseDetail purchaseDetail4;
	private PurchaseDetail purchaseDetail5;
	private PurchaseDetail purchaseDetail6;

	@Before
	public void setup(){
		client1 = clientRepository.save(
			Client.builder()
				.name("TestClient1")
				.lastname("TestClient1Lastname")
				.document("564631")
				.phone("+595971100100")
				.email("testclient1@email.com")
				.active(Boolean.TRUE)
				.build()
		);
		seller1 = sellerRepository.save(
			Seller.builder()
				.code("SELL-1")
				.document("0-11")
				.description("SELLERTEST1")
				.active(Boolean.TRUE)
				.build()
		);
		product1 = productRepository.save(
			Product.builder()
				.sku("sku-1")
				.skuVendor("sku-vendor-1")
				.cost(BigDecimal.ONE)
				.salePrice(BigDecimal.TEN)
				.description("product-1-description")
				.active(Boolean.TRUE)
				.stockQty(10)
				.seller(seller1)
				.build()
		);
		purchase1 = purchaseRepository.save(
			Purchase.builder()
				.receiptNumber("random-value-1")
				.total(new BigDecimal(100))
				.approved(Boolean.TRUE)
				.client(client1)
				.build()
		);
		purchaseDetail1 = PurchaseDetail.builder()
			.product(product1)
			.quantity(10)
			.purchase(purchase1)
			.unitSalePrice(new BigDecimal(5))
			.totalSalePrice(new BigDecimal(50))
			.build();

		purchaseDetail2 = PurchaseDetail.builder()
			.product(product1)
			.quantity(20)
			.purchase(purchase1)
			.unitSalePrice(new BigDecimal(5))
			.totalSalePrice(new BigDecimal(100))
			.build();

		purchaseDetail3 = PurchaseDetail.builder()
			.product(product1)
			.quantity(30)
			.purchase(purchase1)
			.unitSalePrice(new BigDecimal(5))
			.totalSalePrice(new BigDecimal(150))
			.build();

		purchaseDetail4 = PurchaseDetail.builder()
			.product(product1)
			.quantity(40)
			.purchase(purchase1)
			.unitSalePrice(new BigDecimal(5))
			.totalSalePrice(new BigDecimal(200))
			.build();

		purchaseDetail5 = PurchaseDetail.builder()
			.product(product1)
			.quantity(50)
			.purchase(purchase1)
			.unitSalePrice(new BigDecimal(5))
			.totalSalePrice(new BigDecimal(250))
			.build();

		purchaseDetail6 = PurchaseDetail.builder()
			.product(product1)
			.quantity(60)
			.purchase(purchase1)
			.unitSalePrice(new BigDecimal(5))
			.totalSalePrice(new BigDecimal(300))
			.build();
	}

	@Test
	public void testSavePurchaseDetail(){
		PurchaseDetail savedEntity = repository.save(purchaseDetail1);
		assertThat(savedEntity.getId()).isGreaterThan(0);
		assertThat(savedEntity.getPurchase().getId()).isEqualTo(purchase1.getId());
		assertThat(savedEntity.getProduct().getId()).isEqualTo(product1.getId());
	}

	@Test
	public void testFindByPurchaseId(){
		PurchaseDetail savedEntity1 = repository.save(purchaseDetail1);
		PurchaseDetail savedEntity2 = repository.save(purchaseDetail2);
		PurchaseDetail savedEntity3 = repository.save(purchaseDetail3);
		List<PurchaseDetail> savedEntities = repository.findAllByPurchaseId(purchase1.getId());
		assertThat(savedEntity1).isIn(savedEntities);
		assertThat(savedEntity2).isIn(savedEntities);
		assertThat(savedEntity3).isIn(savedEntities);
	}

	@Test
	public void testFindByProductId(){
		PurchaseDetail savedEntity1 = repository.save(purchaseDetail1);
		PurchaseDetail savedEntity2 = repository.save(purchaseDetail2);
		PurchaseDetail savedEntity3 = repository.save(purchaseDetail3);
		List<PurchaseDetail> savedEntities = repository.findAllByProductId(product1.getId());
		assertThat(savedEntity1).isIn(savedEntities);
		assertThat(savedEntity2).isIn(savedEntities);
		assertThat(savedEntity3).isIn(savedEntities);
	}

	@Test
	public void testFindByPurchaseIdPageable(){
		PurchaseDetail savedEntity1 = repository.save(purchaseDetail1);
		PurchaseDetail savedEntity2 = repository.save(purchaseDetail2);
		PurchaseDetail savedEntity3 = repository.save(purchaseDetail3);
		PurchaseDetail savedEntity4 = repository.save(purchaseDetail4);
		PurchaseDetail savedEntity5 = repository.save(purchaseDetail5);
		PurchaseDetail savedEntity6 = repository.save(purchaseDetail6);

		Page<PurchaseDetail> purchaseDetailPage =
			repository.findAllByPurchaseId(purchase1.getId(), PageRequest.of(0, 2));
		assertThat(purchaseDetailPage.getTotalElements()).isEqualTo(6);
		assertThat(purchaseDetailPage.getTotalPages()).isEqualTo(3);
		assertThat(purchaseDetailPage.getContent().get(0)).isNotNull();
	}

	@Test
	public void testFindByProductIdPageable(){
		PurchaseDetail savedEntity1 = repository.save(purchaseDetail1);
		PurchaseDetail savedEntity2 = repository.save(purchaseDetail2);
		PurchaseDetail savedEntity3 = repository.save(purchaseDetail3);
		PurchaseDetail savedEntity4 = repository.save(purchaseDetail4);
		PurchaseDetail savedEntity5 = repository.save(purchaseDetail5);
		PurchaseDetail savedEntity6 = repository.save(purchaseDetail6);

		Page<PurchaseDetail> purchaseDetailPage =
			repository.findAllByProductId(purchase1.getId(), PageRequest.of(0, 2));
		assertThat(purchaseDetailPage.getTotalElements()).isEqualTo(6);
		assertThat(purchaseDetailPage.getTotalPages()).isEqualTo(3);
		assertThat(purchaseDetailPage.getContent().get(0)).isNotNull();
	}
}
