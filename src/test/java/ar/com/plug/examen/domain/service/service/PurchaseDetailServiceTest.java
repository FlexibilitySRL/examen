package ar.com.plug.examen.domain.service.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDetailDto;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseDetail;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseDetailRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.impl.PurchaseDetailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseDetailServiceTest
{
	@InjectMocks
	private PurchaseDetailServiceImpl service;

	@Mock
	private PurchaseDetailRepository repository;

	@Mock
	private PurchaseRepository purchaseRepository;

	@Mock
	private ProductRepository productRepository;

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
		client1 = Client.builder()
			.id(1L)
			.name("TestClient1")
			.lastname("TestClient1Lastname")
			.document("564631")
			.phone("+595971100100")
			.email("testclient1@email.com")
			.active(Boolean.TRUE)
			.build();
		seller1 = Seller.builder()
			.id(1L)
			.code("SELL-1")
			.document("0-11")
			.description("SELLERTEST1")
			.active(Boolean.TRUE)
			.build();
		product1 = Product.builder()
			.id(1L)
			.sku("sku-1")
			.skuVendor("sku-vendor-1")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-1-description")
			.active(Boolean.TRUE)
			.stockQty(10)
			.seller(seller1)
			.build();
		purchase1 = Purchase.builder()
			.id(1L)
			.receiptNumber("random-value-1")
			.total(new BigDecimal(100))
			.approve(Boolean.TRUE)
			.client(client1)
			.build();
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
	public void getPurchaseDetailByIdTest()
	{
		purchaseDetail1.setId(10L);
		when(repository.findById(10L)).thenReturn(Optional.ofNullable(purchaseDetail1));
		PurchaseDetail detailFromDatabase = service.getPurchaseDetailById(10L);
		assertThat(detailFromDatabase.getId()).isEqualTo(purchaseDetail1.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getPurchaseDetailByIdNullThrowsNoSuchElementExceptionTest()
	{
		service.getPurchaseDetailById(null);
	}

	@Test
	public void getAllDetailsPaginated()
	{
		int pageSize = 2;
		int pageNumber = 0;
		List<PurchaseDetail> page1Details = Arrays.asList(purchaseDetail1, purchaseDetail2);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<PurchaseDetail> detailsPage = new PageImpl<>(page1Details);
		when(repository.findAll(pageable)).thenReturn(detailsPage);

		PageDto<PurchaseDetail> detailsPageTest = service.getAllDetailsPageable(pageNumber, pageSize);
		assertThat(detailsPageTest.getContent().size()).isEqualTo(pageSize);
		assertThat(detailsPageTest.getContent().get(0).getPurchase().getId()).isEqualTo(purchase1.getId());
		assertThat(detailsPageTest.getContent().get(1).getPurchase().getId()).isEqualTo(purchase1.getId());
	}

	@Test(expected = ValidationException.class)
	public void savePurchaseDetailThrowsExceptionTest() throws ValidationException
	{
		service.saveDetail(null);
	}

	@Test
	public void savePurchaseDetailTest() throws ValidationException
	{
		PurchaseDetailDto dto = new PurchaseDetailDto(purchaseDetail1.getProduct().getId(),
			purchaseDetail1.getQuantity(), purchaseDetail1.getPurchase().getId(), purchaseDetail1.getUnitSalePrice(),
			purchaseDetail1.getTotalSalePrice());
		when(repository.save(any(PurchaseDetail.class))).thenReturn(purchaseDetail1);
		when(purchaseRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(purchase1));
		when(productRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(product1));
		PurchaseDetail savedDetail = service.saveDetail(dto);
		assertThat(savedDetail).isNotNull();
		assertThat(savedDetail.getPurchase().getId()).isEqualTo(purchase1.getId());
	}

	@Test
	public void updatePurchaseDetailTest() throws ValidationException
	{
		BigDecimal updatedTotal = new BigDecimal(750);
		purchase1.setApprove(false);
		PurchaseDetailDto dto = new PurchaseDetailDto(purchaseDetail1.getProduct().getId(),
			purchaseDetail1.getQuantity(), purchaseDetail1.getPurchase().getId(), purchaseDetail1.getUnitSalePrice(),
			updatedTotal);
		when(purchaseRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(purchase1));
		when(repository.findById(any(Long.class))).thenReturn(Optional.ofNullable(purchaseDetail1));
		when(productRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(product1));
		when(repository.save(any(PurchaseDetail.class))).thenReturn(purchaseDetail1);

		PurchaseDetail updatedPurchase = service.updateDetail(1L, dto);
		assertThat(updatedPurchase).isNotNull();
		assertThat(updatedPurchase.getTotalSalePrice()).isEqualTo(updatedTotal);
	}

	@Test(expected = ValidationException.class)
	public void updatePurchaseDetailApprovedPurchaseTest() throws ValidationException
	{
		BigDecimal updatedTotal = new BigDecimal(750);
		purchase1.setApprove(true);
		PurchaseDetailDto dto = new PurchaseDetailDto(purchaseDetail1.getProduct().getId(),
			purchaseDetail1.getQuantity(), purchaseDetail1.getPurchase().getId(), purchaseDetail1.getUnitSalePrice(),
			updatedTotal);
		when(purchaseRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(purchase1));
		when(repository.findById(any(Long.class))).thenReturn(Optional.ofNullable(purchaseDetail1));
		when(productRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(product1));
		when(repository.save(any(PurchaseDetail.class))).thenReturn(purchaseDetail1);

		service.updateDetail(1L, dto);
	}

	@Test(expected = ValidationException.class)
	public void updatePurchaseDetailWrongId() throws ValidationException
	{
		service.updateDetail(1L, new PurchaseDetailDto());
	}

	@Test(expected = ValidationException.class)
	public void updatePurchaseDetailNullId() throws ValidationException
	{
		service.updateDetail(null, new PurchaseDetailDto());
	}

	@Test(expected = ValidationException.class)
	public void updatePurchaseDetailNullDto() throws ValidationException
	{
		service.updateDetail(1L, null);
	}
}
