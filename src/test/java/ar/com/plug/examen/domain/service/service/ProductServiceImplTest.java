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
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
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
public class ProductServiceImplTest
{
	@InjectMocks
	private ProductServiceImpl service;

	@Mock
	private ProductRepository repository;

	@Mock
	private SellerRepository sellerRepository;

	private Product product1;
	private Product product2;
	private Product product3;
	private Product product4;
	private Product product5;
	private Product product6;

	private Seller seller1;

	@Before
	public void setup()
	{
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
			.seller(seller1)
			.build();

		product2 = Product.builder()
			.sku("sku-2")
			.skuVendor("sku-vendor-2")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-2-description")
			.active(Boolean.TRUE)
			.stockQty(20)
			.seller(seller1)
			.build();

		product3 = Product.builder()
			.sku("sku-3")
			.skuVendor("sku-vendor-3")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-3-description")
			.active(Boolean.TRUE)
			.stockQty(30)
			.seller(seller1)
			.build();

		product4 = Product.builder()
			.sku("sku-4")
			.skuVendor("sku-vendor-4")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-4-description")
			.active(Boolean.TRUE)
			.stockQty(40)
			.seller(seller1)
			.build();

		product5 = Product.builder()
			.sku("sku-5")
			.skuVendor("sku-vendor-5")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-5-description")
			.active(Boolean.TRUE)
			.stockQty(50)
			.seller(seller1)
			.build();

		product6 = Product.builder()
			.sku("sku-6")
			.skuVendor("sku-vendor-6")
			.cost(BigDecimal.ONE)
			.salePrice(BigDecimal.TEN)
			.description("product-6-description")
			.active(Boolean.TRUE)
			.stockQty(60)
			.seller(seller1)
			.build();
	}

	@Test
	public void mockNotNull()
	{
		assertThat(repository).isNotNull();
		assertThat(service).isNotNull();
	}

	@Test
	public void getProductByIdTest()
	{
		product1.setId(10L);
		when(repository.findById(10L)).thenReturn(Optional.ofNullable(product1));
		Product productFromService = service.getProductById(10L);
		assertThat(productFromService.getId()).isEqualTo(product1.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getProductByIdNullThrowsNoSuchElementExceptionTest()
	{
		service.getProductById(null);
	}

	@Test
	public void getProductByCodeTest()
	{
		when(repository.findBySku("sku-2")).thenReturn(product2);
		Product productFromService = service.getProductBySku("sku-2");
		assertThat(productFromService.getId()).isEqualTo(product2.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getProductCodeNullThrowsNoSuchElementExceptionTest()
	{
		service.getProductBySku(null);
	}

	@Test
	public void getAllProductsPaginated()
	{
		int pageSize = 2;
		int pageNumber = 0;
		List<Product> page1Products = Arrays.asList(product1, product2);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Product> productPage = new PageImpl<>(page1Products);
		when(repository.findAll(pageable)).thenReturn(productPage);

		PageDto<Product> productPageTest = service.getAllProductsPageable(pageNumber, pageSize);
		assertThat(productPageTest.getContent().size()).isEqualTo(pageSize);
		assertThat(productPageTest.getContent().get(0).getSku()).isEqualTo(product1.getSku());
		assertThat(productPageTest.getContent().get(1).getSku()).isEqualTo(product2.getSku());
	}

	@Test(expected = ValidationException.class)
	public void saveProductThrowsExceptionTest() throws ValidationException
	{
		service.saveProduct(null);
	}

	@Test
	public void saveProductTest() throws ValidationException
	{
		product1.setId(1L);
		ProductDto dto = new ProductDto(product1.getSku(), product1.getSkuVendor(), product1.getCost(),
			product1.getSalePrice(), product1.getDescription(), product1.getActive(), product1.getSeller().getId(),
			product1.getStockQty());
		when(repository.save(any(Product.class))).thenReturn(product1);
		when(sellerRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(seller1));
		Product savedProduct = service.saveProduct(dto);
		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getSku()).isEqualTo(dto.getSku());
	}

	@Test
	public void updateProductTest() throws ValidationException
	{
		String updatedSku = "updated-sku";
		product1.setId(1L);
		product1.setSku(updatedSku);

		ProductDto dto = new ProductDto(product1.getSku(), product1.getSkuVendor(), product1.getCost(),
			product1.getSalePrice(), product1.getDescription(), product1.getActive(), product1.getSeller().getId(),
			product1.getStockQty());
		when(repository.existsById(any(Long.class))).thenReturn(true);
		when(repository.findBySku(any(String.class))).thenReturn(product1);
		when(sellerRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(seller1));
		when(repository.save(any(Product.class))).thenReturn(product1);

		Product updatedProduct = service.updateProduct(1L, dto);
		assertThat(updatedProduct).isNotNull();
		assertThat(updatedProduct.getSku()).isEqualTo(updatedSku);
	}

	@Test(expected = NoSuchElementException.class)
	public void updateProductWrongId() throws ValidationException
	{
		service.updateProduct(1L, new ProductDto());
	}

	@Test(expected = ValidationException.class)
	public void updateProductNullId() throws ValidationException
	{
		service.updateProduct(null, new ProductDto());
	}

	@Test(expected = ValidationException.class)
	public void updateProductNullDto() throws ValidationException
	{
		service.updateProduct(1L, null);
	}

	@Test(expected = ValidationException.class)
	public void inactiveProductNullId() throws ValidationException
	{
		service.inactivateProduct(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void inactivateProductWrongId() throws ValidationException
	{
		service.inactivateProduct(1L);
	}

	@Test
	public void inactivateProductTest() throws ValidationException
	{
		product1.setId(1L);
		product1.setActive(false);
		when(repository.findById(1L)).thenReturn(Optional.ofNullable(product1));
		when(repository.save(any(Product.class))).thenReturn(product1);
		Product inactiveProduct = service.inactivateProduct(1L);
		assertThat(inactiveProduct).isNotNull();
		assertThat(inactiveProduct.getActive()).isFalse();
		assertThat(inactiveProduct.getId()).isEqualTo(product1.getId());
	}
}
