/**
 * 
 */
package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.domain.exception.SellerNotFoundException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.impl.SellerServiceImpl;

/**
 * @author ro
 *
 */
@RunWith(SpringRunner.class)
public class SellerServiceTest {

	@TestConfiguration
    static class SellerServiceImplTestContextConfiguration {
  
        @Bean
        public SellerService sellerService() {
            return new SellerServiceImpl();
        }
    }
	
    @Autowired
    private SellerService sellerService;
	 
    @MockBean
    private SellerRepository sellerRepository;
    
    @MockBean
    private ProductRepository productRepository;
    
    
    @Before
    public void setUp() {
    	Seller seller = Mockito.mock(Seller.class);
    	Mockito.when(seller.getIdSeller()).thenReturn(1l);
    	Mockito.when(seller.getProductList()).thenReturn(
    			Arrays.asList(
        				 Mockito.mock(Product.class),
        				 Mockito.mock(Product.class)
        ));
    	Mockito.when(sellerRepository.findOne(1l)).thenReturn(seller);
    }
	
    @Test
    public void getProductsOfSeller() throws SellerNotFoundException {
        Seller seller = sellerRepository.findOne(1l);
        assertFalse(seller.getProductList().isEmpty());
    }
    
    @Test(expected= SellerNotFoundException.class)
    public void getProductsOfInvalidSeller() throws SellerNotFoundException {
        assertTrue(sellerService.getProductsOfSeller(2l).isEmpty());
    }
}
