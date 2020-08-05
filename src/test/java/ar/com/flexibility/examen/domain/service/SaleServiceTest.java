package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.ConstantsProps;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.build.ClientBuilder;
import ar.com.flexibility.examen.domain.build.ProductBuilder;
import ar.com.flexibility.examen.domain.build.SalesBuilder;
import ar.com.flexibility.examen.domain.build.SellerBuilder;
import ar.com.flexibility.examen.domain.enu.SaleStatus;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Sale;
import ar.com.flexibility.examen.domain.repository.SaleRepository;
import ar.com.flexibility.examen.domain.service.impl.SaleServiceImpl;

@RunWith(SpringRunner.class)
public class SaleServiceTest {
	
	// ---------------
	// Constants
	// ---------------
	private static final String CODE = "PR123456";

	// ---------------
	// Attributes
	// ---------------
	private static Sale entity;

	// ---------------
	// Mocks
	// ---------------
	@Mock
	private ConstantsProps constants;
	@Mock
	private MessagesProps messages;
	@Mock
	private SaleRepository saleRepository;
	@Mock
	private ClientService clientService;
	@Mock
	private ProductService productService;
	@Mock
	private SellerService sellerService;
	
	@InjectMocks
	private SaleServiceImpl saleService;

	// ---------------
	// Methods
	// ---------------	
	@BeforeClass
	public static void initClass () {
		entity = SalesBuilder.builder().build();
	}
	

	@Test (expected = ServiceException.class)
	public void getEntityUnexpectedError () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		doThrow(Exception.class).when(this.saleRepository).getFirstByCode(code);
		
		// Action
		this.saleService.getEntity(code);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void getEntityNotFundException () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		when(this.saleRepository.getFirstByCode(code)).thenReturn(null);
		
		// Action
		this.saleService.getEntity(code);
		
		// Assert
	}
	
	@Test
	public void getEntitySuccess () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		when(this.saleRepository.getFirstByCode(code)).thenReturn(entity);
		
		// Action
		Sale response = this.saleService.getEntity(code);
		
		// Assert
        verify (this.saleRepository, times (1)).getFirstByCode(code);
		assertEquals(response.getCode(), entity.getCode());
		assertEquals(response.getAmount(), entity.getAmount());
		assertEquals(response.getStatus(), entity.getStatus());
	}
	
	@Test (expected = ServiceException.class)
	public void getClientUnexpectedError () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		doThrow(Exception.class).when(this.saleRepository).getFirstByCode(code);
		
		// Action
		this.saleService.getSale(code);
		
		// Assert
		
	}
	
	@Test
	public void getClientSuccess () throws ServiceException {
		// Arrange 
		String code = CODE;
		
		when(this.saleRepository.getFirstByCode(code)).thenReturn(entity);
		
		// Action
		SaleApiResponse response = this.saleService.getSale(code);
		
		// Assert
        verify (this.saleRepository, times (1)).getFirstByCode(code);
		assertEquals(response.getCode(), entity.getCode());
		assertEquals(response.getProductAmount(), entity.getAmount());
		assertEquals(response.getStatus(), entity.getStatus().name());
	}

	@Test (expected = ServiceException.class)
	public void getListUnexpectedError () throws ServiceException {
		// Arrange 
		doThrow(Exception.class).when(this.saleRepository).findAll();
		
		// Action
		this.saleService.list();
		
		// Assert
	}
	
	@Test
	public void getListNull () throws ServiceException {
		// Arrange 
		when(this.saleRepository.findAll()).thenReturn(null);
		
		// Action
		List<SaleApiResponse> data = this.saleService.list();
		
		// Assert
        verify (this.saleRepository, times (1)).findAll();
        assertTrue(data.isEmpty());
	}
	
	@Test
	public void getListSuccess () throws ServiceException {
		// Arrange 
		List<Sale> response = Arrays.asList(entity);
		
		when(this.saleRepository.findAll()).thenReturn(response);
		
		// Action
		List<SaleApiResponse> data = this.saleService.list();
		
		// Assert
        verify (this.saleRepository, times (1)).findAll();
        assertEquals(data.size(), response.size());
		assertEquals(data.get(0).getCode(), entity.getCode());
		assertEquals(data.get(0).getProductAmount(), entity.getAmount());
		assertEquals(data.get(0).getStatus(), entity.getStatus().name());
	}
	
	@Test (expected = ServiceException.class)
	public void getListByStatusUnexpectedError () throws ServiceException {
		// Arrange 
		SaleStatus status = SaleStatus.PENDIENTE;
		
		doThrow(Exception.class).when(this.saleRepository).findByStatus(status);
		
		// Action
		this.saleService.getSalesByStatus(status.name());
		
		// Assert
	}
	
	@Test
	public void getListByStatusNull () throws ServiceException {
		// Arrange 
		SaleStatus status = SaleStatus.PENDIENTE;
		
		when(this.saleRepository.findByStatus(status)).thenReturn(null);
		
		// Action
		List<SaleApiResponse> data = this.saleService.getSalesByStatus(status.name());
		
		// Assert
        verify (this.saleRepository, times (1)).findByStatus(status);
        assertTrue(data.isEmpty());
	}
	
	@Test
	public void getListByStatusSuccess () throws ServiceException {
		// Arrange 
		SaleStatus status = SaleStatus.PENDIENTE;
		List<Sale> response = Arrays.asList(entity);
		
		when(this.saleRepository.findByStatus(status)).thenReturn(response);
		
		// Action
		List<SaleApiResponse> data = this.saleService.getSalesByStatus(status.name());
		
		// Assert
        verify (this.saleRepository, times (1)).findByStatus(status);
        assertEquals(data.size(), response.size());
		assertEquals(data.get(0).getCode(), entity.getCode());
		assertEquals(data.get(0).getProductAmount(), entity.getAmount());
		assertEquals(data.get(0).getStatus(), entity.getStatus().name());
	}
	
	@Test(expected = ServiceException.class)
	public void approvedUnexpectedError() throws ServiceException {
		// Arrange
		String code = CODE;
		entity.setStatus(SaleStatus.PENDIENTE);

		when(this.saleRepository.getFirstByCode(code)).thenReturn(entity);
		doThrow(Exception.class).when(this.saleRepository).save(any(Sale.class));
		
		// Action
		this.saleService.approveSale(code);
		
		// Assert
	}
	
	@Test(expected = ServiceException.class)
	public void approvedSaleNotFoundError() throws ServiceException {
		// Arrange
		String code = CODE;
		
		when(this.saleRepository.getFirstByCode(code)).thenReturn(null);
		
		// Action
		this.saleService.approveSale(code);
		
		// Assert
	}
	
	@Test(expected = ServiceException.class)
	public void approvedSaleAlreadyApproved() throws ServiceException {
		// Arrange
		String code = CODE;
		Sale response = SalesBuilder.builder().build();
		response.setStatus(SaleStatus.APROBADO);
		
		when(this.saleRepository.getFirstByCode(code)).thenReturn(response);
		
		// Action
		this.saleService.approveSale(code);
		
		// Assert
	}
	
	@Test
	public void approvedSuccess() throws ServiceException {
		// Arrange
		String code = CODE;
		entity.setStatus(SaleStatus.PENDIENTE);
		
		when(this.saleRepository.getFirstByCode(code)).thenReturn(entity);
		
		// Action
		this.saleService.approveSale(code);
		
		// Assert
        verify (this.saleRepository, times (1)).save(any(Sale.class));
	}
	
	@Test (expected = ServiceException.class)
	public void newSaleUnexpectedError() throws ServiceException {
		// Arrange
		String code = CODE;
		String clientIdentifier = "C123456";
		int productAmount = 50;
		String productCode = "P001";
		String sellerIdentifier = "S123456";
		
		when(this.saleRepository.getFirstByCode(code)).thenReturn(null);
		doThrow(Exception.class).when(this.productService).getEntity(productCode);
		
		// Action
		this.saleService.newSale(code, clientIdentifier, sellerIdentifier, productCode, productAmount);
		
		// Assert
	
	}

	@Test (expected = ServiceException.class)
	public void newSaleCodeExistsError() throws ServiceException {
		// Arrange
		String code = CODE;
		String clientIdentifier = "C123456";
		int productAmount = 50;
		String productCode = "P001";
		String sellerIdentifier = "S123456";
		
		when(this.saleRepository.getFirstByCode(code)).thenReturn(entity);
		
		// Action
		this.saleService.newSale(code, clientIdentifier, sellerIdentifier, productCode, productAmount);
		
		// Assert
	
	}

	@Test (expected = ServiceException.class)
	public void newSaleAmountError() throws ServiceException {
		// Arrange
		String code = CODE;
		String clientIdentifier = "C123456";
		int productAmount = 70;
		String productCode = "P001";
		String sellerIdentifier = "S123456";
		
		when(this.constants.getProductMinAmount()).thenReturn(1);
		when(this.saleRepository.getFirstByCode(code)).thenReturn(null);
		when(this.productService.getEntity(productCode)).thenReturn(ProductBuilder.builder().build());
		
		// Action
		this.saleService.newSale(code, clientIdentifier, sellerIdentifier, productCode, productAmount);
		
		// Assert
	}

	@Test
	public void newSaleSuccess() throws ServiceException {
		// Arrange
		String code = CODE;
		String clientIdentifier = "C123456";
		int productAmount = 20;
		String productCode = "P001";
		String sellerIdentifier = "S123456";
		
		when(this.constants.getProductMinAmount()).thenReturn(1);
		when(this.saleRepository.getFirstByCode(code)).thenReturn(null);
		when(this.clientService.getEntity(clientIdentifier)).thenReturn(ClientBuilder.builder().build());
		when(this.productService.getEntity(productCode)).thenReturn(ProductBuilder.builder().build());
		when(this.sellerService.getEntity(sellerIdentifier)).thenReturn(SellerBuilder.builder().build());
		
		// Action
		this.saleService.newSale(code, clientIdentifier, sellerIdentifier, productCode, productAmount);
		
		// Assert
        verify (this.saleRepository, times (1)).getFirstByCode(code);
        verify (this.clientService, times (1)).getEntity(clientIdentifier);
        verify (this.productService, times (1)).getEntity(productCode);
        verify (this.sellerService, times (1)).getEntity(sellerIdentifier);
        verify (this.saleRepository, times (1)).save(any(Sale.class));
        verify (this.productService, times (1)).updateProductAmount(any(Product.class), anyInt());
	}
	
}
