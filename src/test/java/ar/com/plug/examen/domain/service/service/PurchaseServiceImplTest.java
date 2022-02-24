package ar.com.plug.examen.domain.service.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDto;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseDetail;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseDetailRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;
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
public class PurchaseServiceImplTest
{
	@InjectMocks
	private PurchaseServiceImpl service;

	@Mock
	private PurchaseRepository repository;

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private PurchaseDetailRepository purchaseDetailRepository;

	@Mock
	private ProductRepository productRepository;

	private Purchase purchase1;
	private Purchase purchase2;
	private Purchase purchase3;
	private Purchase purchase4;
	private Purchase purchase5;
	private Purchase purchase6;

	private Product product1;
	private Seller seller1;
	private PurchaseDetail purchaseDetail1;
	private PurchaseDetail purchaseDetail2;
	private PurchaseDetail purchaseDetail3;
	private Client client1;

	@Before
	public void setup()
	{
		client1 = Client.builder()
			.id(1L)
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
			.approve(Boolean.TRUE)
			.client(client1)
			.build();

		purchase2 = Purchase.builder()
			.receiptNumber("random-value-2")
			.total(new BigDecimal(200))
			.approve(Boolean.TRUE)
			.client(client1)
			.build();

		purchase3 = Purchase.builder()
			.receiptNumber("random-value-3")
			.total(new BigDecimal(300))
			.approve(Boolean.TRUE)
			.build();

		purchase4 = Purchase.builder()
			.receiptNumber("random-value-4")
			.total(new BigDecimal(400))
			.approve(Boolean.TRUE)
			.client(client1)
			.build();

		purchase5 = Purchase.builder()
			.receiptNumber("random-value-5")
			.total(new BigDecimal(500))
			.approve(Boolean.TRUE)
			.client(client1)
			.build();

		purchase6 = Purchase.builder()
			.receiptNumber("random-value-6")
			.total(new BigDecimal(600))
			.approve(Boolean.TRUE)
			.client(client1)
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
	}

	@Test
	public void mockNotNull()
	{
		assertThat(repository).isNotNull();
		assertThat(service).isNotNull();
	}

	@Test
	public void getPurchaseByIdTest()
	{
		purchase1.setId(10L);
		when(repository.findById(10L)).thenReturn(Optional.ofNullable(purchase1));
		Purchase purchaseFromService = service.getPurchaseById(10L);
		assertThat(purchaseFromService.getId()).isEqualTo(purchase1.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getPurchaseByIdNullThrowsNoSuchElementExceptionTest()
	{
		service.getPurchaseById(null);
	}

	@Test
	public void getPurchaseByCodeTest()
	{
		when(repository.findByReceiptNumber("random-value-2")).thenReturn(purchase2);
		Purchase purchaseFromService = service.getPurchaseByReceiptNumber("random-value-2");
		assertThat(purchaseFromService.getId()).isEqualTo(purchase2.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getPurchaseCodeNullThrowsNoSuchElementExceptionTest()
	{
		service.getPurchaseByReceiptNumber(null);
	}

	@Test
	public void getAllPurchasesPaginated()
	{
		int pageSize = 2;
		int pageNumber = 0;
		List<Purchase> page1Purchases = Arrays.asList(purchase1, purchase2);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Purchase> purchasePage = new PageImpl<>(page1Purchases);
		when(repository.findAll(pageable)).thenReturn(purchasePage);

		PageDto<Purchase> purchasePageTest = service.getAllPurchasesPageable(pageNumber, pageSize);
		assertThat(purchasePageTest.getContent().size()).isEqualTo(pageSize);
		assertThat(purchasePageTest.getContent().get(0).getReceiptNumber()).isEqualTo(purchase1.getReceiptNumber());
		assertThat(purchasePageTest.getContent().get(1).getReceiptNumber()).isEqualTo(purchase2.getReceiptNumber());
	}

	@Test(expected = ValidationException.class)
	public void savePurchaseThrowsExceptionTest() throws ValidationException
	{
		service.savePurchase(null);
	}

	@Test
	public void savePurchaseTest() throws ValidationException
	{
		purchase1.setId(1L);
		PurchaseDto dto = new PurchaseDto(purchase1.getReceiptNumber(),
			purchase1.getApprove(), purchase1.getClient().getId());
		when(repository.save(any(Purchase.class))).thenReturn(purchase1);
		when(clientRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(client1));
		Purchase savedPurchase = service.savePurchase(dto);
		assertThat(savedPurchase).isNotNull();
		assertThat(savedPurchase.getReceiptNumber()).isEqualTo(dto.getReceiptNumber());
	}

	@Test
	public void updatePurchaseTest() throws ValidationException
	{
		String updatedReceiptNumber = "updated-receiptNumber";
		purchase1.setId(1L);
		purchase1.setReceiptNumber(updatedReceiptNumber);

		PurchaseDto dto = new PurchaseDto(purchase1.getReceiptNumber(),
			purchase1.getApprove(), purchase1.getClient().getId());
		when(repository.existsById(any(Long.class))).thenReturn(true);
		when(repository.findByReceiptNumber(any(String.class))).thenReturn(purchase1);
		when(clientRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(client1));
		when(repository.save(any(Purchase.class))).thenReturn(purchase1);

		Purchase updatedPurchase = service.updatePurchase(1L, dto);
		assertThat(updatedPurchase).isNotNull();
		assertThat(updatedPurchase.getReceiptNumber()).isEqualTo(updatedReceiptNumber);
	}

	@Test(expected = NoSuchElementException.class)
	public void updatePurchaseWrongId() throws ValidationException
	{
		service.updatePurchase(1L, new PurchaseDto());
	}

	@Test(expected = ValidationException.class)
	public void updatePurchaseNullId() throws ValidationException
	{
		service.updatePurchase(null, new PurchaseDto());
	}

	@Test(expected = ValidationException.class)
	public void updatePurchaseNullDto() throws ValidationException
	{
		service.updatePurchase(1L, null);
	}

	@Test(expected = ValidationException.class)
	public void approvePurchaseNullId() throws ValidationException
	{
		service.approvePurchase(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void approvePurchaseWrongId() throws ValidationException
	{
		service.approvePurchase(100L);
	}

	@Test(expected = ValidationException.class)
	public void approvePurchaseExceedStockQtyTest() throws ValidationException
	{
		purchase1.setId(1L);
		purchase1.setApprove(false);
		when(repository.findById(1L)).thenReturn(Optional.ofNullable(purchase1));
		when(repository.save(any(Purchase.class))).thenReturn(purchase1);
		when(purchaseDetailRepository.findAllByPurchaseId(any(Long.class))).thenReturn(
			Arrays.asList(purchaseDetail1, purchaseDetail2, purchaseDetail3)
		);
		Purchase approvedPurchase = service.approvePurchase(1L);
		assertThat(approvedPurchase).isNotNull();
		assertThat(approvedPurchase.getApprove()).isTrue();
		assertThat(approvedPurchase.getId()).isEqualTo(purchase1.getId());
	}

	@Test
	public void approvePurchaseTest() throws ValidationException
	{
		purchase1.setId(1L);
		purchase1.setApprove(false);
		product1.setStockQty(1000000);
		when(repository.findById(1L)).thenReturn(Optional.ofNullable(purchase1));
		when(repository.save(any(Purchase.class))).thenReturn(purchase1);
		when(purchaseDetailRepository.findAllByPurchaseId(any(Long.class))).thenReturn(
			Arrays.asList(purchaseDetail1, purchaseDetail2, purchaseDetail3)
		);
		when(productRepository.saveAll(any())).thenReturn(Collections.emptyList());
		Purchase approvedPurchase = service.approvePurchase(1L);
		assertThat(approvedPurchase).isNotNull();
		assertThat(approvedPurchase.getApprove()).isTrue();
		assertThat(approvedPurchase.getId()).isEqualTo(purchase1.getId());
	}
}
