package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.dao.IProductDao;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;
import ar.com.plug.examen.exception.BusinessException;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;
    
    @Mock
    private IProductDao dao;
    
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
    	List<Product> list = new ArrayList<Product>();
    	Product product1 = new Product(1l, "TestProduct1", new BigDecimal("10.50"));
    	Product product2 = new Product(1l, "TestProduct1", new BigDecimal("10.50"));
    	Product product3 = new Product(1l, "TestProduct1", new BigDecimal("10.50"));
    	list.add(product1);
    	list.add(product2);
    	list.add(product3);
    	
    	when(dao.findAll()).thenReturn(list);
    	
    	List<Product> productList = productService.findAll();

        assertNotNull(productList);
        verify(dao, times(1)).findAll();
    }
    
    @Test
    public void saveProductTest() {
    	Product product = new Product(1l, "TestProduct1", new BigDecimal("10.50"));
    	productService.save(product);
    	verify(dao, times(1)).save(product);
    }
    
    @Test
    public void findByIdTest() {
    	when(dao.findById(1l)).thenReturn(Optional.of(new Product(1l, "TestProduct1", new BigDecimal("10.50"))));
    	
    	Product product = productService.findById(1l);
    	
    	assertEquals("TestProduct1", product.getName());
    	assertEquals(new BigDecimal("10.50"), product.getPrice());
    	
    }
    
    @Test(expected = BusinessException.class)
    public void findByIdExceptionTest() {
    	when(dao.findById(1l)).thenReturn(Optional.empty());
    	
    	productService.findById(1l);
    	
    }
    
    @Test
    public void deleteTest() {
    	
    	Product product = new Product(1l, "TestProduct1", new BigDecimal("10.50"));
		when(dao.findById(1l)).thenReturn(Optional.of(product)).thenReturn(Optional.of(product)).thenReturn(null);
    	
    	Product persistedProduct = productService.findById(1l);
    	
    	productService.delete(persistedProduct.getId());
    	
    	Optional<Product> nullProduct = dao.findById(1l);
    	
    	assertNull(nullProduct);
    	
    	verify(dao, times(1)).delete(product);
    }
    
    @Test(expected = BusinessException.class)
    public void deleteTestException() {
    	
    	Product product = new Product(1l, "TestProduct1", new BigDecimal("10.50"));
		when(dao.findById(1l)).thenReturn(Optional.of(product)).thenReturn(Optional.of(product)).thenReturn(null);
		
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		purchaseList.add(new Purchase());
		
    	when(purchaseService.findByProduct(1l)).thenReturn(purchaseList);
    	when(message.get(BusinessExceptionConstants.CANT_DELETE_PRODUCT)).thenReturn("mensaje");
    	
    	Product persistedProduct = productService.findById(1l);

    	productService.delete(persistedProduct.getId());
    	
    }
    

    
}
