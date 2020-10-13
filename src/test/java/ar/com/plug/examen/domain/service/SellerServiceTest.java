package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.dao.ISellerDao;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;
import ar.com.plug.examen.exception.BusinessException;

@RunWith(MockitoJUnitRunner.class)
public class SellerServiceTest {

    @InjectMocks
    private SellerServiceImpl sellerService;
    
    @Mock
    private ISellerDao dao;
    
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
    	List<Seller> list = new ArrayList<Seller>();
    	Seller seller1 = new Seller(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	Seller seller2 = new Seller(2l, "TestName1", "TestSurname2", "test1@email.com", null);
    	Seller seller3 = new Seller(3l, "TestName1", "TestSurname3", "test1@email.com", null);
    	list.add(seller1);
    	list.add(seller2);
    	list.add(seller3);
    	
    	when(dao.findAll()).thenReturn(list);
    	
    	List<Seller> sellerList = sellerService.findAll();

        assertNotNull(sellerList);
        verify(dao, times(1)).findAll();
    }
    
    @Test
    public void saveSellerTest() {
    	Seller seller = new Seller(1l, "TestName1", "TestSurname1", "test1@email.com", null);
    	sellerService.save(seller);
    	verify(dao, times(1)).save(seller);
    }
    
    @Test
    public void findByIdTest() {
    	when(dao.findById(1l)).thenReturn(Optional.of(new Seller(1l, "TestName1", "TestSurname1", "test1@email.com", null)));
    	
    	Seller seller = sellerService.findById(1l);
    	
    	assertEquals("TestName1", seller.getName());
    	assertEquals("test1@email.com", seller.getEmail());
    	
    }
    
    @Test(expected = BusinessException.class)
    public void findByIdExceptionTest() {
    	when(dao.findById(1l)).thenReturn(Optional.empty());
    	
    	sellerService.findById(1l);
    	
    }
    
    @Test
    public void deleteTest() {
    	
    	Seller seller = new Seller(1l, "TestName1", "TestSurname1", "test1@email.com", null);
		when(dao.findById(1l)).thenReturn(Optional.of(seller)).thenReturn(Optional.of(seller)).thenReturn(null);
    	
    	Seller persistedSeller = sellerService.findById(1l);
    	
    	sellerService.delete(persistedSeller.getId());
    	
    	Optional<Seller> nullSeller = dao.findById(1l);
    	
    	assertNull(nullSeller);
    	
    	verify(dao, times(1)).delete(seller);
    }
    
    @Test(expected = BusinessException.class)
    public void deleteTestException() {
    	
    	Seller seller = new Seller(1l, "TestName1", "TestSurname1", "test1@email.com", null);
		when(dao.findById(1l)).thenReturn(Optional.of(seller)).thenReturn(Optional.of(seller)).thenReturn(null);
		
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		purchaseList.add(new Purchase());
		
    	when(purchaseService.findBySellerAndStatus(seller, PurchaseStatus.PENDING)).thenReturn(purchaseList);
    	when(message.get(BusinessExceptionConstants.CANT_DELETE_SELLER_PENDING_PURCHASES)).thenReturn("mensaje");
    	
    	Seller persistedSeller = sellerService.findById(1l);

    	sellerService.delete(persistedSeller.getId());
    	
    }
    
}