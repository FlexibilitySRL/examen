package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.domain.enums.PurchaseStatus;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.dao.IClientDao;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;
import ar.com.plug.examen.exception.BusinessException;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;
    
    @Mock
    private IClientDao dao;
    
    @Mock
    private PurchaseServiceImpl purchaseService;
    
    @Mock
    private MessageSourceProvider message;
    
    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
    	List<Client> list = new ArrayList<Client>();
    	Client client1 = new Client(1l, "TestName1", "TestSurname1", "test1@email.com",null);
    	Client client2 = new Client(2l, "TestName2", "TestSurname2", "test2@email.com", null);
    	Client client3 = new Client(3l, "TestName3", "TestSurname3", "test3@email.com", null);
    	list.add(client1);
    	list.add(client2);
    	list.add(client3);
    	
    	when(dao.findAll()).thenReturn(list);
    	
    	List<Client> persistedList = clientService.findAll();

        assertNotNull(persistedList);
        verify(dao, times(1)).findAll();
        assertTrue(list.size() == persistedList.size() && list.containsAll(persistedList) && persistedList.containsAll(list));
    }
    
    @Test
    public void saveClientTest() {
    	Client client = new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	clientService.save(client);
    	verify(dao, times(1)).save(client);
    }
    
    @Test
    public void findByIdTest() {
    	when(dao.findById(1l)).thenReturn(Optional.of(new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null)));
    	
    	Client client = clientService.findById(1l);
    	
    	assertEquals("TestName1", client.getName());
    	assertEquals("TestSurname1", client.getSurname());
    	assertEquals("test1@email.com", client.getEmail());
    	
    }
    
    @Test(expected = BusinessException.class)
    public void findByIdExceptionTest() {
    	when(dao.findById(1l)).thenReturn(Optional.empty());
    	
    	clientService.findById(1l);
    	
    }
    
    @Test
    public void deleteTest() {
    	
    	Client client = new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null);
		when(dao.findById(1l)).thenReturn(Optional.of(client)).thenReturn(Optional.of(client)).thenReturn(null);
    	
    	Client persistedClient = clientService.findById(1l);
    	
    	clientService.delete(persistedClient.getId());
    	
    	Optional<Client> nullClient = dao.findById(1l);
    	
    	assertNull(nullClient);
    	
    	verify(dao, times(1)).delete(client);
    }
    
    @Test(expected = BusinessException.class)
    public void deleteTestException() {
    	
    	Client client = new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null);
		when(dao.findById(1l)).thenReturn(Optional.of(client)).thenReturn(Optional.of(client)).thenReturn(null);
		
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		purchaseList.add(new Purchase());
		
    	when(purchaseService.findByClientAndStatus(client, PurchaseStatus.PENDING)).thenReturn(purchaseList);
    	when(message.get(BusinessExceptionConstants.CANT_DELETE_CLIENT_PENDING_PURCHASES)).thenReturn("mensaje");
    	
    	Client persistedSeller = clientService.findById(1l);

    	clientService.delete(persistedSeller.getId());
    	
    }
    

    
}
