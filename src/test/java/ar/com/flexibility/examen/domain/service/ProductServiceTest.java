package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.api.response.ProductApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.ConstantsProps;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.build.SalesBuilder;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Sale;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	// ---------------
	// Constants
	// ---------------
	private static final int AMOUNT = 10;
	private static final String CODE = "TEST";
	private static final String NAME = "TEST";
	private static final double PRICE = 50.0;
	
	// ---------------
	// Attributes
	// ---------------
	private static Product entity;

	// ---------------
	// Mocks
	// ---------------
	@Mock
	private ConstantsProps constants;
	@Mock
	private MessagesProps messages;
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;

	// ---------------
	// Methods
	// ---------------	
	@BeforeClass
	public static void initClass () {
		entity = new Product();
		entity.setAmount(AMOUNT);
		entity.setCode(CODE);
		entity.setName(NAME);
		entity.setPrice(PRICE);
	}
	
	@Before
	public void init () {
		when(constants.getProductMaxAmount()).thenReturn(11);
		when(constants.getProductMaxPrice()).thenReturn(100.0);
		when(constants.getProductMinAmount()).thenReturn(1);
		when(constants.getProductMinPrice()).thenReturn(0.01);
	}
	
	@Test (expected = ServiceException.class)
	public void deleteUnexpectedError () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		doThrow(Exception.class).when(this.productRepository).deleteByCode(code);
		
		// Action
		this.productService.deleteProduct(code);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void deleteNotFoundRecordError () throws ServiceException {
		// Arrange 
		String code = CODE;
		int result = 0;
		
		when(this.productRepository.deleteByCode(code)).thenReturn(result);
		
		// Action
		this.productService.deleteProduct(code);
		
		// Assert
	}
	
	@Test
	public void deleteSuccess () throws ServiceException {
		// Arrange 
		String code = CODE;
		int result = 1;
		
		when(this.productRepository.deleteByCode(code)).thenReturn(result);
		
		// Action
		this.productService.deleteProduct(code);
		
		// Assert
        verify (this.productRepository, times (1)).deleteByCode(code);
	}
	
	@Test (expected = ServiceException.class)
	public void getEntityUnexpectedError () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		doThrow(Exception.class).when(this.productRepository).getFirstByCode(code);
		
		// Action
		this.productService.getEntity(code);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void getEntityNotFundException () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		when(this.productRepository.getFirstByCode(code)).thenReturn(null);
		
		// Action
		this.productService.getEntity(code);
		
		// Assert
	}
	
	@Test
	public void getEntitySuccess () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		when(this.productRepository.getFirstByCode(code)).thenReturn(entity);
		
		// Action
		Product response = this.productService.getEntity(code);
		
		// Assert
        verify (this.productRepository, times (1)).getFirstByCode(code);
		assertEquals(response.getAmount(), entity.getAmount());
		assertEquals(response.getCode(), entity.getCode());
		assertEquals(response.getName(), entity.getName());
		assertEquals(response.getPrice(), entity.getPrice(), 2D);
	}
	
	@Test (expected = ServiceException.class)
	public void getProductUnexpectedError () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		doThrow(Exception.class).when(this.productRepository).getFirstByCode(code);
		
		// Action
		this.productService.getProduct(code);
		
		// Assert
	}
	
	@Test
	public void getProductSuccess () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		when(this.productRepository.getFirstByCode(code)).thenReturn(entity);
		
		// Action
		ProductApiResponse response = this.productService.getProduct(code);
		
		// Assert
        verify (this.productRepository, times (1)).getFirstByCode(code);
		assertEquals(response.getAmount(), entity.getAmount());
		assertEquals(response.getCode(), entity.getCode());
		assertEquals(response.getName(), entity.getName());
		assertEquals(response.getPrice(), entity.getPrice(), 2D);
	}
	
	@Test (expected = ServiceException.class)
	public void getListUnexpectedError () throws ServiceException {
		// Arrange 
		doThrow(Exception.class).when(this.productRepository).findAll();
		
		// Action
		this.productService.list();
		
		// Assert
	}
	
	@Test
	public void getListNull () throws ServiceException {
		// Arrange 
		
		when(this.productRepository.findAll()).thenReturn(null);
		
		// Action
		List<ProductApiResponse> data = this.productService.list();
		
		// Assert
        verify (this.productRepository, times (1)).findAll();
        assertTrue(data.isEmpty());
	}
	
	@Test
	public void getListSuccess () throws ServiceException {
		// Arrange 
		List<Sale> sales = Arrays.asList(SalesBuilder.builder().build());
		entity.setSales(sales);
		List<Product> response = Arrays.asList(entity);
		
		when(this.productRepository.findAll()).thenReturn(response);
		
		// Action
		List<ProductApiResponse> data = this.productService.list();
		
		// Assert
        verify (this.productRepository, times (1)).findAll();
		assertEquals(data.size(), response.size());
		assertEquals(data.get(0).getAmount(), entity.getAmount());
		assertEquals(data.get(0).getCode(), entity.getCode());
		assertEquals(data.get(0).getName(), entity.getName());
		assertEquals(data.get(0).getPrice(), entity.getPrice(), 2D);
	}
	
	@Test (expected = ServiceException.class)
	public void updateAmountUnexpectedError () throws ServiceException {
		// Arrange
		int amount = 5;
		
		doThrow(Exception.class).when(productRepository).save(any(Product.class));
		
		// Action
		this.productService.updateProductAmount(entity, amount);
		
		// Assert
		
	}
	
	@Test (expected = ServiceException.class)
	public void updateAmountInvalidMaxValue () throws ServiceException {
		// Arrange
		int amount = 400;
		
		// Action
		this.productService.updateProductAmount(entity, amount);
		
		// Assert
		
	}
	
	@Test (expected = ServiceException.class)
	public void updateAmountInvalidMinValue () throws ServiceException {
		// Arrange
		int amount = -1;
		
		// Action
		this.productService.updateProductAmount(entity, amount);
		
		// Assert
		
	}
	
	@Test
	public void updateAmountSuccess () throws ServiceException {
		// Arrange
		int amount = 5;
		
		when(productRepository.save(any(Product.class))).thenReturn(entity);
		
		// Action
		this.productService.updateProductAmount(entity, amount);
		
		// Assert
        verify (this.productRepository, times (1)).save(any(Product.class));
	}
	
	@Test (expected = ServiceException.class)
	public void saveProductUnexpectedError () throws ServiceException {
		// Arrange
		String code = CODE;
		
		doThrow(Exception.class).when(productRepository).getFirstByCode(code);
		
		// Action
		this.productService.newProduct(code, NAME, AMOUNT, PRICE);
		
		// Assert
		
	}
	
	@Test (expected = ServiceException.class)
	public void saveProductAlreadyExists () throws ServiceException {
		// Arrange
		String code = CODE;
		
		when(productRepository.getFirstByCode(code)).thenReturn(entity);
		
		// Action
		this.productService.newProduct(code, NAME, AMOUNT, PRICE);
		
		// Assert
		
	}

	@Test (expected = ServiceException.class)
	public void saveProductPriceMaxException () throws ServiceException {
		// Arrange
		String code = CODE;
		double price = 1000.0;
		
		when(productRepository.getFirstByCode(code)).thenReturn(null);
		
		// Action
		this.productService.newProduct(code, NAME, AMOUNT, price);
		
		// Assert
		verify (this.productRepository, times(1)).save(any(Product.class));
		
	}

	@Test (expected = ServiceException.class)
	public void saveProductPriceMinException () throws ServiceException {
		// Arrange
		String code = CODE;
		double price = 0.0;
		
		when(productRepository.getFirstByCode(code)).thenReturn(null);
		
		// Action
		this.productService.newProduct(code, NAME, AMOUNT, price);
		
		// Assert
		verify (this.productRepository, times(1)).save(any(Product.class));
		
	}
	
	@Test
	public void saveProductSuccess () throws ServiceException {
		// Arrange
		String code = CODE;
		double price = PRICE;
		
		when(productRepository.getFirstByCode(code)).thenReturn(null);
		
		// Action
		this.productService.newProduct(code, NAME, AMOUNT, price);
		
		// Assert
		verify (this.productRepository, times(1)).save(any(Product.class));
		
	}
	
	@Test (expected = ServiceException.class)
	public void updateProductCurrentCodeNotFound () throws ServiceException {

		// Arrange
		String code = CODE;
		String newCode = null;
		double price = PRICE;

		when(productRepository.getFirstByCode(code)).thenReturn(null);
		
		// Action
		this.productService.updateProduct(code, newCode, NAME, AMOUNT, price);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void updateProductNewCodeExists () throws ServiceException {

		// Arrange
		String code = CODE;
		String newCode = "TEST2";
		double price = PRICE;

		when(productRepository.getFirstByCode(code)).thenReturn(entity);
		when(productRepository.getFirstByCode(newCode)).thenReturn(entity);
		
		// Action
		this.productService.updateProduct(code, newCode, NAME, AMOUNT, price);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void updateProductUnexcectedError () throws ServiceException {

		// Arrange
		String code = CODE;
		String newCode = "TEST2";
		double price = PRICE;

		when(productRepository.getFirstByCode(code)).thenReturn(entity);
		when(productRepository.getFirstByCode(newCode)).thenReturn(null);
		doThrow(Exception.class).when(productRepository).save(any(Product.class));
		
		// Action
		this.productService.updateProduct(code, newCode, NAME, AMOUNT, price);
		
		// Assert
	}
	
	@Test 
	public void updateProductSuccess () throws ServiceException {

		// Arrange
		String code = CODE;
		String newCode = "";
		double price = PRICE;

		when(productRepository.getFirstByCode(code)).thenReturn(entity);
		when(productRepository.getFirstByCode(newCode)).thenReturn(null);
		
		// Action
		this.productService.updateProduct(code, newCode, NAME, AMOUNT, price);
		
		// Assert
		verify (this.productRepository, times(1)).save(any(Product.class));
	}
	
}
