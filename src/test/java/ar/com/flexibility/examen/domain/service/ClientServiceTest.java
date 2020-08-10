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

import ar.com.flexibility.examen.app.api.response.ClientApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.ConstantsProps;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.build.SalesBuilder;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Sale;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;

@RunWith(SpringRunner.class)
public class ClientServiceTest {
	
	// ---------------
	// Constants
	// ---------------
	private static final String IDENTIFIER = "123456";
	private static final String NAME = "TEST";
	private static final String SURNAME = "SURTEST";

	// ---------------
	// Attributes
	// ---------------
	private static Client entity;

	// ---------------
	// Mocks
	// ---------------
	@Mock
	private ConstantsProps constants;
	@Mock
	private MessagesProps messages;
	@Mock
	private ClientRepository clientRepository;
	@Mock
	private ValidatorService validatorService;
	
	@InjectMocks
	private ClientServiceImpl clientService;

	// ---------------
	// Methods
	// ---------------	
	@BeforeClass
	public static void initClass () {
		entity = new Client();
		entity.setIdentifier(IDENTIFIER);
		entity.setName(NAME);
		entity.setSurname(SURNAME);
	}
	
	@Test (expected = ServiceException.class)
	public void deleteNotFoundRecordError () throws ServiceException {
		// Arrange 
		String identifier = "TEST";
		int result = 0;
		
		when(this.clientRepository.deleteByIdentifier(identifier)).thenReturn(result);
		
		// Action
		this.clientService.delete(identifier);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void deleteUnexpectedError () throws ServiceException {
		// Arrange 
		String identifier = "TEST";
		
		doThrow(Exception.class).when(this.clientRepository).deleteByIdentifier(identifier);
		
		// Action
		this.clientService.delete(identifier);
		
		// Assert
	}
	
	@Test
	public void deleteSuccess () throws ServiceException {
		// Arrange 
		String identifier = "TEST";
		int result = 1;
		
		when(this.clientRepository.deleteByIdentifier(identifier)).thenReturn(result);
		
		// Action
		this.clientService.delete(identifier);
		
		// Assert
        verify (this.clientRepository, times (1)).deleteByIdentifier(identifier);
	}

	@Test (expected = ServiceException.class)
	public void getEntityUnexpectedError () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		doThrow(Exception.class).when(this.clientRepository).getFirstByIdentifier(identifier);
		
		// Action
		this.clientService.getEntity(identifier);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void getEntityNotFundException () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(null);
		
		// Action
		this.clientService.getEntity(identifier);
		
		// Assert
	}
	
	@Test
	public void getEntitySuccess () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		
		// Action
		Client response = this.clientService.getEntity(identifier);
		
		// Assert
        verify (this.clientRepository, times (1)).getFirstByIdentifier(identifier);
		assertEquals(response.getIdentifier(), entity.getIdentifier());
		assertEquals(response.getName(), entity.getName());
		assertEquals(response.getSurname(), entity.getSurname());
	}
	
	@Test (expected = ServiceException.class)
	public void getClientUnexpectedError () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		doThrow(Exception.class).when(this.clientRepository).getFirstByIdentifier(identifier);
		
		// Action
		this.clientService.get(identifier);
		
		// Assert
	}
	
	@Test
	public void getClientSuccess () throws ServiceException {
		// Arrange 
		String identifier = IDENTIFIER;
		
		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		
		// Action
		ClientApiResponse response = this.clientService.get(identifier);
		
		// Assert
        verify (this.clientRepository, times (1)).getFirstByIdentifier(identifier);
		assertEquals(response.getIdentifier(), entity.getIdentifier());
		assertEquals(response.getName(), entity.getName());
		assertEquals(response.getSurname(), entity.getSurname());
	}
	
	@Test (expected = ServiceException.class)
	public void getListUnexpectedError () throws ServiceException {
		// Arrange 
		doThrow(Exception.class).when(this.clientRepository).findAll();
		
		// Action
		this.clientService.list();
		
		// Assert
	}
	
	@Test
	public void getListNull () throws ServiceException {
		// Arrange 
		
		when(this.clientRepository.findAll()).thenReturn(null);
		
		// Action
		List<ClientApiResponse> data = this.clientService.list();
		
		// Assert
        verify (this.clientRepository, times (1)).findAll();
        assertTrue(data.isEmpty());
	}
	
	@Test
	public void getListSuccess () throws ServiceException {
		// Arrange 
		List<Sale> purchases = Arrays.asList(SalesBuilder.builder().build());
		entity.setPurchases(purchases);
		List<Client> response = Arrays.asList(entity);
		
		when(this.clientRepository.findAll()).thenReturn(response);
		
		// Action
		List<ClientApiResponse> data = this.clientService.list();
		
		// Assert
        verify (this.clientRepository, times (1)).findAll();
		assertEquals(data.size(), response.size());
		assertEquals(data.get(0).getIdentifier(), entity.getIdentifier());
		assertEquals(data.get(0).getName(), entity.getName());
		assertEquals(data.get(0).getSurname(), entity.getSurname());
	}

	@Test (expected = ServiceException.class)
	public void saveUnexpectedError () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;

		doThrow(Exception.class).when(this.clientRepository).getFirstByIdentifier(identifier);
		
		// Action
		this.clientService.save(identifier, NAME, SURNAME);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void saveIdentifierExists () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;

		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		
		// Action
		this.clientService.save(identifier, NAME, SURNAME);
		
		// Assert
	}
	
	@Test
	public void saveSuccess () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;

		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(null);
		
		// Action
		this.clientService.save(identifier, NAME, SURNAME);
		
		// Assert
		verify (this.clientRepository, times(1)).save(any(Client.class));
	}
	
	@Test (expected = ServiceException.class)
	public void updateUnexpectedError () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;
		String newIdentifier = "123";

		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		when(this.clientRepository.getFirstByIdentifier(newIdentifier)).thenReturn(null);
		doThrow(Exception.class).when(this.clientRepository).save(any(Client.class));
		
		// Action
		this.clientService.update(identifier, newIdentifier, NAME, SURNAME);
		
		// Assert
	}
	
	@Test (expected = ServiceException.class)
	public void updateCurrentIdentifierExists () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;
		String newIdentifier = null;

		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(null);
		
		// Action
		this.clientService.update(identifier, newIdentifier, NAME, SURNAME);
		
		// Assert
		
	}
	
	@Test (expected = ServiceException.class)
	public void updateNewIdentifierExists () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;
		String newIdentifier = "123";

		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		when(this.clientRepository.getFirstByIdentifier(newIdentifier)).thenReturn(entity);
		
		// Action
		this.clientService.update(identifier, newIdentifier, NAME, SURNAME);
		
		// Assert
	}
	
	@Test 
	public void updateSuccess () throws ServiceException {
		// Arrange
		String identifier = IDENTIFIER;
		String newIdentifier = "123";

		when(this.clientRepository.getFirstByIdentifier(identifier)).thenReturn(entity);
		when(this.clientRepository.getFirstByIdentifier(newIdentifier)).thenReturn(null);
		
		// Action
		this.clientService.update(identifier, newIdentifier, NAME, SURNAME);
		
		// Assert
		verify (this.clientRepository, times(1)).save(any(Client.class));
	}
	
}
