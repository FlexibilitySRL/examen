/**
 * 
 */
package ar.com.flexibility.examen.domain.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.OrderApi;
import ar.com.flexibility.examen.domain.exception.ClientNotFoundException;
import ar.com.flexibility.examen.domain.exception.EmptyOrderException;
import ar.com.flexibility.examen.domain.exception.InsufficientBalanceException;
import ar.com.flexibility.examen.domain.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.exception.ProductWithoutStock;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;

/**
 * @author ro
 *
 */
@RunWith(SpringRunner.class)
public class ClientServiceTest {
	
	@TestConfiguration
    static class ClientServiceImplTestContextConfiguration {
  
        @Bean
        public ClientService clientService() {
            return new ClientServiceImpl();
        }
    }
 
    @Autowired
    private ClientService clientService;
 
    @MockBean
    private ClientRepository clientRepository;
    
    @MockBean
    private ProductRepository productRepository;
    
    @MockBean
    private SellerRepository sellerRepository;
    
    @Before
    public void setUp() {
    	setUpClient();
    	setUpProduct();
        Mockito.when(productRepository.findAll())
        .thenReturn(
       		 Arrays.asList(
       				 Mockito.mock(Product.class),
       				 Mockito.mock(Product.class)
         ));  
    }
	
    private void setUpProduct() {
       	Seller seller = Mockito.mock(Seller.class);
    	Mockito.when(seller.getIdSeller()).thenReturn(1l);
    	Product product = Mockito.mock(Product.class);
    	Mockito.when(product.getIdProduct()).thenReturn(1l);
    	Mockito.when(product.getSeller()).thenReturn(seller);
    	Mockito.when(product.getPrice()).thenReturn(100D);
    	Mockito.when(product.getStock()).thenReturn(2);
    	
    	Mockito.when(productRepository.findOne(1l)).thenReturn(product);
    	
    }
    
    private void setUpClient() {
    	Client client = Mockito.mock(Client.class);
    	Mockito.when(client.getIdClient()).thenReturn(1l);
    	Mockito.when(client.getBalance()).thenReturn(10D);
    	Mockito.when(client.getPurchaseList()).thenReturn(
    			Arrays.asList(
        				 Mockito.mock(Purchase.class),
        				 Mockito.mock(Purchase.class)
        ));
    	Mockito.when(clientRepository.findOne(1l)).thenReturn(client);
    }
    
	@Test
	public void  whenValidIdClient_thenClientShouldBeFound() throws ClientNotFoundException {
	    ClientApi client  = clientService.getClientApi(1l);
	    assertEquals(1L,client.getIdClient().longValue());
	}
	
	@Test(expected=ClientNotFoundException.class)
	public void  whenInvalidIdClient_thenClientNotShoudBeFound() throws ClientNotFoundException {
	    clientService.getClientApi(2l);
	}
	
	@Test
	public void findAllPurchaseOfClient() throws ClientNotFoundException {
		Client client = clientRepository.findOne(1l);
	    assertFalse(client.getPurchaseList().isEmpty());
	}
	
	@Test
	public void findAllProduct(){
	    assertFalse(productRepository.findAll().isEmpty());
	}	
	
	@Test(expected=EmptyOrderException.class)
	public void makePurhaseWithEmptyOrder() throws ProductNotFoundException, ProductWithoutStock, InsufficientBalanceException, EmptyOrderException, ClientNotFoundException{
	   clientService.processPurchase(1l, null);
	}	
	
	@Test(expected=ProductNotFoundException.class)
	public void makePurhaseWithInvalidIdProduct() throws ProductNotFoundException, ProductWithoutStock, InsufficientBalanceException, EmptyOrderException, ClientNotFoundException{
    	OrderApi order = new OrderApi(2l,2);
    	List<OrderApi> orders = new ArrayList<>();
    	orders.add(order);
		clientService.processPurchase(1l,orders );
	}	
	
	@Test(expected=ProductWithoutStock.class)
	public void makePurhaseWithProductWithoutStock() throws ProductNotFoundException, ProductWithoutStock, InsufficientBalanceException, EmptyOrderException, ClientNotFoundException{
    	OrderApi order = new OrderApi(1l,3);
    	List<OrderApi> orders = new ArrayList<>();
    	orders.add(order);
		clientService.processPurchase(1l,orders);
	}	
	
	@Test(expected=InsufficientBalanceException.class)
	public void makePurhaseWithInsufficientBalance() throws ProductNotFoundException, ProductWithoutStock, InsufficientBalanceException, EmptyOrderException, ClientNotFoundException{
    	OrderApi order = new OrderApi(1l,1);
    	List<OrderApi> orders = new ArrayList<>();
    	orders.add(order);
		clientService.processPurchase(1l,orders);
	}
	
	@Test(expected=ClientNotFoundException.class)
	public void makePurhaseWithInvalidClient() throws ProductNotFoundException, ProductWithoutStock, InsufficientBalanceException, EmptyOrderException, ClientNotFoundException{
    	OrderApi order = new OrderApi(1l,1);
    	List<OrderApi> orders = new ArrayList<>();
    	orders.add(order);
		clientService.processPurchase(2l,orders);
	}
	
}
