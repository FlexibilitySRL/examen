package ar.com.plug.examen.domain.service.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ProductRepository;
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
public class ProductRepositoryTest
{
	@Autowired
	private ProductRepository repository;

	@Autowired
	private SellerRepository sellerRepository;
	
	private Product product1;
	private Product product2;
	private Product product3;
	private Product product4;
	private Product product5;
	private Product product6;
	
	private Seller seller1;
	@Before
	public void setup() {
		seller1 = Seller.builder()
			.id(10L)
			.code("SELL-1")
			.document("0-11")
			.description("SELLERTEST1")
			.active(Boolean.TRUE)
			.build();
		
		product1 = Product.builder()
			.sku("sku-1")
			.skuVendor("sku-vendor-1")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-1-description")
			.active(Boolean.TRUE)
			.stockQty(10)
			.build();

		product2 = Product.builder()
			.sku("sku-2")
			.skuVendor("sku-vendor-2")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-2-description")
			.active(Boolean.TRUE)
			.stockQty(20)
			.build();

		product3 = Product.builder()
			.sku("sku-3")
			.skuVendor("sku-vendor-3")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-3-description")
			.active(Boolean.TRUE)
			.stockQty(30)
			.build();

		product4 = Product.builder()
			.sku("sku-4")
			.skuVendor("sku-vendor-4")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-4-description")
			.active(Boolean.TRUE)
			.stockQty(40)
			.build();

		product5 = Product.builder()
			.sku("sku-5")
			.skuVendor("sku-vendor-5")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-5-description")
			.active(Boolean.TRUE)
			.stockQty(50)
			.build();

		product6 = Product.builder()
			.sku("sku-6")
			.skuVendor("sku-vendor-6")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-6-description")
			.active(Boolean.TRUE)
			.stockQty(60)
			.build();
	}

	@Test
	public void autowiredNotNull()
	{
		assertThat(repository).isNotNull();
		assertThat(sellerRepository).isNotNull();
	}

	@Test
	public void testSaveProduct()
	{
		Seller savedSeller = sellerRepository.save(seller1);
		product1.setSeller(savedSeller);
		Product savedEntity = repository.save(product1);
		assertThat(savedEntity.getId()).isGreaterThan(0);
		assertThat(savedEntity.getActive()).isEqualTo(Boolean.TRUE);
		assertThat(savedEntity.getSku()).isEqualTo("sku-1");
		assertThat(savedEntity.getDescription()).isEqualTo("product-1-description");
		assertThat(savedEntity.getSeller().getId()).isEqualTo(savedSeller.getId());
	}

	@Test
	public void testFindProductBySku()
	{
		Seller savedSeller = sellerRepository.save(seller1);
		product1.setSeller(savedSeller);
		Product savedEntity = repository.save(product1);
		assertThat(savedEntity).isNotNull();
		assertThat(savedEntity.getSku()).isEqualTo(repository.findBySku("sku-1").getSku());
	}

	@Test
	public void testGetProductsPageable()
	{
		Seller savedSeller = sellerRepository.save(seller1);

		product1.setSeller(savedSeller);
		repository.save(product1);

		product2.setSeller(savedSeller);
		repository.save(product2);

		product3.setSeller(savedSeller);
		repository.save(product3);

		product4.setSeller(savedSeller);
		repository.save(product4);

		product5.setSeller(savedSeller);
		repository.save(product5);

		Page<Product> page1 = repository.findAll(PageRequest.of(0, 3));
		assertThat(page1).isNotNull();
		assertThat(page1.getSize()).isEqualTo(3);
		assertThat(page1.getContent().get(0).getSku()).isEqualTo("sku-1");
		assertThat(page1.getContent().get(1).getSku()).isEqualTo("sku-2");

		Page<Product> page2 = repository.findAll(PageRequest.of(1, 3));
		assertThat(page2).isNotNull();
		assertThat(page2.getSize()).isEqualTo(3);
		assertThat(page2.getContent().get(0).getSku()).isEqualTo("sku-4");
		assertThat(page2.getContent().get(1).getSku()).isEqualTo("sku-5");
	}
}
