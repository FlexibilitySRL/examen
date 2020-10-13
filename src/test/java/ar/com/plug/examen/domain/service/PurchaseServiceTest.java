package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseItem;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.dao.IClientDao;
import ar.com.plug.examen.domain.model.dao.IPurchaseDao;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;
import ar.com.plug.examen.exception.BusinessException;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

    @InjectMocks
    private PurchaseServiceImpl purchaseService;
    
    @Mock
    private ClientServiceImpl clientService;
    
    @Mock
    private SellerServiceImpl sellerService;
    
    @Mock
    private ProductServiceImpl productService;
    
    @Mock
    private IPurchaseDao dao;
    
    @Mock
    private IClientDao clientDAO;
    
    @Mock
    private MessageSourceProvider message;
    
    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest() {
		when(dao.findById(1l)).thenReturn(Optional.of(new Purchase(1l, "TestDescripcion1", null, null, null, PurchaseStatus.PENDING)));
    	
    	Purchase purchase = purchaseService.findById(1l);
    	
    	assertEquals("TestDescripcion1", purchase.getDescription());
    	assertEquals(PurchaseStatus.PENDING, purchase.getStatus());
    	
    }
    
    @Test(expected = BusinessException.class)
    public void findByIdExceptionTest() {
		when(dao.findById(1l)).thenReturn(Optional.empty());
    	purchaseService.findById(1l);
    }
    
    @Test
    public void findAllTest() {
    	List<Purchase> list = new ArrayList<Purchase>();
    	Purchase purchase1 = new Purchase(1l, "TestDesc1", null, null, null, PurchaseStatus.APPROVED);
    	Purchase purchase2 = new Purchase(2l, "TestDesc2", null, null, null, PurchaseStatus.APPROVED);
    	Purchase purchase3 = new Purchase(3l, "TestDesc3", null, null, null, PurchaseStatus.APPROVED);
    	list.add(purchase1);
    	list.add(purchase2);
    	list.add(purchase3);
    	
    	when(dao.findAll()).thenReturn(list);
    	
    	List<Purchase> persistedList = purchaseService.findAll();

        assertNotNull(persistedList);
        verify(dao, times(1)).findAll();
        assertTrue(list.size() == persistedList.size() && list.containsAll(persistedList) && persistedList.containsAll(list));
    }
    
    @Test
    public void findByProductTest() {
    	
    	Product product = new Product(1l, "TestProduct1", new BigDecimal("10.50"));
    	
    	List<PurchaseItem> itemList = new ArrayList<PurchaseItem>();
    	itemList.add(new PurchaseItem(1l, 10, product));
    	
    	String purchaseDescription = "TestDescripcion1";
		Purchase purchase1 = new Purchase(1l, purchaseDescription, null, null, itemList, PurchaseStatus.PENDING);
		
		List<Purchase> list = new ArrayList<Purchase>();
		list.add(purchase1);
    	
		when(dao.findByProduct(product)).thenReturn(list);
		when(productService.findById(1l)).thenReturn(product);
    	
		List<Purchase> persistedList = purchaseService.findByProduct(1l);
    	
		assertNotNull(persistedList);
    	assertTrue(list.size() == persistedList.size() && list.containsAll(persistedList) && persistedList.containsAll(list));
    }
    
    @Test
    public void findByClientTest() {
    	
    	Client client = new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	
    	List<Purchase> list = new ArrayList<Purchase>();
    	Purchase purchase1 = new Purchase(1l, "TestDescripcion1", client, null, null, PurchaseStatus.CANCELED);
    	Purchase purchase2 = new Purchase(2l, "TestDescripcion1", client, null, null, PurchaseStatus.PENDING);
    	Purchase purchase3 = new Purchase(3l, "TestDescripcion1", client, null, null, PurchaseStatus.APPROVED);
    	
    	list.add(purchase1);
    	list.add(purchase2);
    	list.add(purchase3);
    	
		when(dao.findByClient(client)).thenReturn(list);
    	
		List<Purchase> persistedList = purchaseService.findByClient(client);
    	
		assertNotNull(persistedList);
    	assertTrue(list.size() == persistedList.size() && list.containsAll(persistedList) && persistedList.containsAll(list));
    }
    
    @Test
    public void findByClientAndStatusTest() {
    	
    	Client client = new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	
    	List<Purchase> list = new ArrayList<Purchase>();
    	Purchase purchase1 = new Purchase(1l, "TestDescripcion1", client, null, null, PurchaseStatus.APPROVED);
    	Purchase purchase2 = new Purchase(2l, "TestDescripcion1", client, null, null, PurchaseStatus.APPROVED);
    	
    	list.add(purchase1);
    	list.add(purchase2);
    	
		when(dao.findByClientAndStatus(client, PurchaseStatus.APPROVED)).thenReturn(list);
    	
		List<Purchase> persistedList = purchaseService.findByClientAndStatus(client, PurchaseStatus.APPROVED);
    	
		assertNotNull(persistedList);
    	assertTrue(list.size() == persistedList.size() && list.containsAll(persistedList) && persistedList.containsAll(list));
    }
    
    @Test
    public void findBySellerAndStatusTest() {
    	
    	Seller seller = new Seller(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	
    	List<Purchase> list = new ArrayList<Purchase>();
    	Purchase purchase1 = new Purchase(1l, "TestDescripcion1", null, seller, null, PurchaseStatus.APPROVED);
    	Purchase purchase2 = new Purchase(2l, "TestDescripcion2", null, seller, null, PurchaseStatus.APPROVED);
    	
    	list.add(purchase1);
    	list.add(purchase2);
    	
		when(dao.findBySellerAndStatus(seller, PurchaseStatus.APPROVED)).thenReturn(list);
    	
		List<Purchase> persistedList = purchaseService.findBySellerAndStatus(seller, PurchaseStatus.APPROVED);
    	
		assertNotNull(persistedList);
    	assertTrue(list.size() == persistedList.size() && list.containsAll(persistedList) && persistedList.containsAll(list));
    }

    @Test
    public void changeStatusTest() {
    	
    	Client client = new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	
    	when(dao.findById(1l)).thenReturn(Optional.of(new Purchase(1l, "TestDescripcion1", null, null, null, PurchaseStatus.PENDING)))
    		.thenReturn(Optional.of(new Purchase(1l, "TestDescripcion1", client, null, null, PurchaseStatus.CANCELED)));
    	
    	purchaseService.changeStatus(1l, PurchaseStatus.CANCELED);
    	
    	Purchase persistedPurchase = purchaseService.findById(1l);
    	
        assertNotNull(persistedPurchase);
        assertEquals(PurchaseStatus.CANCELED, persistedPurchase.getStatus());
        
    }
    
    @Test(expected = BusinessException.class)
    public void changeStatusExceptionTest() {
    	 when(dao.findById(2l)).thenReturn(Optional.of(new Purchase(1l, "TestDescripcion1", null, null, null, PurchaseStatus.APPROVED)));
    	 purchaseService.changeStatus(2l, PurchaseStatus.APPROVED);
    }
    
    @Test(expected = BusinessException.class)
    public void changeStatusNullTest() {
    	 Purchase purchase = purchaseService.changeStatus(3l, PurchaseStatus.APPROVED);
    	 assertNull(purchase);
    }
    
    @Test
    public void bulkChangeStatusTest() {
    	
    	Client client = new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	
    	List<Purchase> listCanceled = new ArrayList<Purchase>();
    	Purchase purchase1 = new Purchase(1l, "TestDescripcion1", client, null, null, PurchaseStatus.CANCELED);
    	Purchase purchase2 = new Purchase(2l, "TestDescripcion1", client, null, null, PurchaseStatus.CANCELED);
    	Purchase purchase3 = new Purchase(3l, "TestDescripcion1", client, null, null, PurchaseStatus.CANCELED);
    	listCanceled.add(purchase1);
    	listCanceled.add(purchase2);
    	listCanceled.add(purchase3);
    	
    	when(dao.findByClientAndStatus(client, PurchaseStatus.CANCELED)).thenReturn(listCanceled);
    	
    	List<Purchase> persistedList = purchaseService.bulkChangeStatus(client, PurchaseStatus.CANCELED);
    	
    	verify(dao, times(1)).bulkChangeStatus(1l, PurchaseStatus.CANCELED);
    	
    	assertNotNull(persistedList);
        assertTrue(listCanceled.size() == persistedList.size() && listCanceled.containsAll(persistedList) && persistedList.containsAll(listCanceled));
        
    }
    
    @Test
    public void deleteTest() {
    	
    	Purchase purchase = new Purchase(1l, "TestDescripcion1", null, null, null, PurchaseStatus.PENDING);
		when(dao.findById(1l)).thenReturn(Optional.of(purchase)).thenReturn(Optional.of(purchase)).thenReturn(null);
    	
    	Purchase persistedPurchase = purchaseService.findById(1l);
    	
    	purchaseService.delete(persistedPurchase.getId());
    	
    	Optional<Purchase> nullPurchase = dao.findById(1l);
    	
    	assertNull(nullPurchase);
    	
    	verify(dao, times(1)).deleteById(1l);
    }
    
    @Test(expected = BusinessException.class)
    public void deleteTestException() {
    	
    	Purchase purchase = new Purchase(1l, "TestDescripcion1", null, null, null, PurchaseStatus.CANCELED);
		when(dao.findById(1l)).thenReturn(Optional.of(purchase)).thenReturn(Optional.of(purchase)).thenReturn(null);
		
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		purchaseList.add(new Purchase());
		
    	when(message.get(BusinessExceptionConstants.CANT_DELETE_NOT_PENDING_PURCHASE)).thenReturn("mensaje");
    	
    	Purchase persistedPurchase = purchaseService.findById(1l);

    	purchaseService.delete(persistedPurchase.getId());
    	
    }
    
    @Test
    public void buyTest() {
    	
    	Client client = new Client(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	
    	Seller seller = new Seller(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	
    	Product product = new Product(1l, "TestProduct1", new BigDecimal("10.50"));
    	
    	List<PurchaseItem> itemList = new ArrayList<PurchaseItem>();
    	itemList.add(new PurchaseItem(1l, 10, product));
    	
    	String purchaseDescription = "TestDescripcion1";
		Purchase purchase1 = new Purchase(1l, purchaseDescription, client, seller, itemList, PurchaseStatus.PENDING);
    	
    	when(dao.findById(1l)).thenReturn(Optional.of(purchase1));
    	
    	HashMap<Long, Integer> items = new HashMap<Long, Integer>();
    	items.put(1l, 10);
    	
    	purchaseService.buy(purchaseDescription, 1l, 1l, items);
    	
    	Purchase persistedPurchase = purchaseService.findById(1l);
    	
    	assertEquals(purchaseDescription, persistedPurchase.getDescription());
    	assertEquals(client, persistedPurchase.getClient());
    	assertEquals(seller, persistedPurchase.getSeller());
    	assertTrue(itemList.size() == persistedPurchase.getItems().size() && itemList.containsAll(persistedPurchase.getItems()) && persistedPurchase.getItems().containsAll(itemList));
    	
    }
    
    @Test(expected = Exception.class)
    public void buyExceptionTest() {
    	
    	when(clientService.findById(1l)).thenThrow(new Exception());
    	
    	HashMap<Long, Integer> items = new HashMap<Long, Integer>();
    	items.put(1l, 10);
    	
    	purchaseService.buy("TestDescripcion1", 1l, 1l, items);
    	
    }
    
}
