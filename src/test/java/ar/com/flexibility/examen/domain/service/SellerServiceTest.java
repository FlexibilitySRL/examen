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

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.api.response.SellerApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.ConstantsProps;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.build.SalesBuilder;
import ar.com.flexibility.examen.domain.model.Sale;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.impl.SellerServiceImpl;

@RunWith(SpringRunner.class)
public class SellerServiceTest {
	
	// ---------------
	// Constants
	// ---------------
	private static final String IDENTIFIER = "123456";
	private static final String NAME = "TEST";
	private static final String SURNAME = "SURTEST";

	// ---------------
	// Attributes
	// ---------------
	private static Seller entity;

	// ---------------
	// Mocks
	// ---------------
	@Mock
	private ConstantsProps constants;
	@Mock
	private MessagesProps messages;
	@Mock
	private SellerRepository sellerRepository;
	@Mock
	private ValidatorService validatorService;
	
	@InjectMocks
	private SellerServiceImpl sellerService;

	// ---------------
	// Methods
	// ---------------	
	@BeforeClass
	public static void initClass () {
		entity = new Seller();
		entity.setIdentifier(IDENTIFIER);
		entity.setName(NAME);
		entity.setSurname(SURNAME);
	}
	
	@Test (expected = ServiceException.class)
	public void deleteNotFoundRecordError () throws ServiceException {
		// Arrange 
		String identifier = "TEST";
		int result = 0;
		
		when(this.sellerRepository.deleteByIdentifier(identifier)).thenReturn(result);
		
		// Action
		this.sellerService.delete(identifier);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void deleteUnexpectedError () throws ServiceException {
		// Arrange 
		String identifier = "TEST";
		
		doThrow(Exception.class).when(this.sellerRepository).deleteByIdentifier(identifier);
		
		// Action
		this.sellerService.delete(identifier);
		
		// Assert
	}
	
	@Test
	public void deleteSuccess () throws ServiceException {
		// Arrange 
		String identifier = "TEST";
		int result = 1;
		
		when(this.sellerRepository.deleteByIdentifier(identifier)).thenReturn(result);
		
		// Action
		this.sellerService.delete(identifier);
		
		// Assert
        verify (this.sellerRepository, times (1)).deleteByIdentifier(identifier);
	}

	
	@Test (expected = ServiceException.class)
	public void getEntityUnexpectedError () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		doThrow(Exception.class).when(this.sellerRepository).getFirstByIdentifier(identifier);
		
		// Action
		this.sellerService.getEntity(identifier);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void getEntityNotFundException () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(null);
		
		// Action
		this.sellerService.getEntity(identifier);
		
		// Assert
	}
	
	@Test
	public void getEntitySuccess () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		
		// Action
		Seller response = this.sellerService.getEntity(identifier);
		
		// Assert
        verify (this.sellerRepository, times (1)).getFirstByIdentifier(identifier);
		assertEquals(response.getIdentifier(), entity.getIdentifier());
		assertEquals(response.getName(), entity.getName());
		assertEquals(response.getSurname(), entity.getSurname());
	}
	
	@Test (expected = ServiceException.class)
	public void getSellerUnexpectedError () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		doThrow(Exception.class).when(this.sellerRepository).getFirstByIdentifier(identifier);
		
		// Action
		this.sellerService.get(identifier);
		
		// Assert
	}
	
	@Test
	public void getSellerSuccess () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		
		// Action
		SellerApiResponse response = this.sellerService.get(identifier);
		
		// Assert
        verify (this.sellerRepository, times (1)).getFirstByIdentifier(identifier);
		assertEquals(response.getIdentifier(), entity.getIdentifier());
		assertEquals(response.getName(), entity.getName());
		assertEquals(response.getSurname(), entity.getSurname());
	}
	
	@Test (expected = ServiceException.class)
	public void getListUnexpectedError () throws ServiceException {
		// Arrange 
		doThrow(Exception.class).when(this.sellerRepository).findAll();
		
		// Action
		this.sellerService.list();
		
		// Assert
	}
	
	@Test
	public void getListNull () throws ServiceException {
		// Arrange 
		
		when(this.sellerRepository.findAll()).thenReturn(null);
		
		// Action
		List<SellerApiResponse> data = this.sellerService.list();
		
		// Assert
        verify (this.sellerRepository, times (1)).findAll();
        assertTrue(data.isEmpty());
	}
	
	@Test
	public void getListSuccess () throws ServiceException {
		// Arrange 
		List<Sale> sales = Arrays.asList(SalesBuilder.builder().build());
		entity.setSales(sales);
		List<Seller> response = Arrays.asList(entity);
		
		when(this.sellerRepository.findAll()).thenReturn(response);
		
		// Action
		List<SellerApiResponse> data = this.sellerService.list();
		
		// Assert
        verify (this.sellerRepository, times (1)).findAll();
		assertEquals(data.size(), response.size());
		assertEquals(data.get(0).getIdentifier(), entity.getIdentifier());
		assertEquals(data.get(0).getName(), entity.getName());
		assertEquals(data.get(0).getSurname(), entity.getSurname());
	}
	
	@Test (expected = ServiceException.class)
	public void saveUnexpectedError () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;

		doThrow(Exception.class).when(this.sellerRepository).getFirstByIdentifier(identifier);
		
		// Action
		this.sellerService.save(identifier, NAME, SURNAME);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void saveIdentifierExists () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;

		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		
		// Action
		this.sellerService.save(identifier, NAME, SURNAME);
		
		// Assert
	}
	
	@Test
	public void saveSuccess () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;

		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(null);
		
		// Action
		this.sellerService.save(identifier, NAME, SURNAME);
		
		// Assert
		verify (this.sellerRepository, times(1)).save(any(Seller.class));
	}
	
	@Test (expected = ServiceException.class)
	public void updateUnexpectedError () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;
		String newIdentifier = "123";

		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		when(this.sellerRepository.getFirstByIdentifier(newIdentifier)).thenReturn(null);
		doThrow(Exception.class).when(this.sellerRepository).save(any(Seller.class));
		
		// Action
		this.sellerService.update(identifier, newIdentifier, NAME, SURNAME);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void updateCurrentIdentifierExists () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;
		String newIdentifier = null;

		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(null);
		
		// Action
		this.sellerService.update(identifier, newIdentifier, NAME, SURNAME);
		
		// Assert
		
	}
	
	@Test (expected = ServiceException.class)
	public void updateNewIdentifierExists () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;
		String newIdentifier = "123";

		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		when(this.sellerRepository.getFirstByIdentifier(newIdentifier)).thenReturn(entity);
		
		// Action
		this.sellerService.update(identifier, newIdentifier, NAME, SURNAME);
		
		// Assert
	}
	
	@Test 
	public void updateSuccess () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;
		String newIdentifier = "123";

		when(this.sellerRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		when(this.sellerRepository.getFirstByIdentifier(newIdentifier)).thenReturn(null);
		
		// Action
		this.sellerService.update(identifier, newIdentifier, NAME, SURNAME);
		
		// Assert
		verify (this.sellerRepository, times(1)).save(any(Seller.class));
	}
		
}
