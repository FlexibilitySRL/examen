package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.service.impl.ProcessSellerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessSellerServiceTest {

    @Autowired
    private ProcessSellerServiceImpl sellerService;
    
    @Test
    public void shouldCreateASeller()
    {
        SellerApi sellerApi = new SellerApi();
        sellerApi.setFirstName("Carl");
        sellerApi.setLastName("Throller");
        
        sellerApi = sellerService.create(sellerApi);
        
        assertNotNull(sellerApi.getId());
        assertEquals(sellerApi.getFirstName(), "Carl");
    }
    
    @Test
    public void shouldUpdateASeller() {
    	SellerApi sellerApi;
    	sellerApi = sellerService.searchByName("Carl");
    	sellerApi.setFirstName("Adam");
        
    	sellerApi = sellerService.update(sellerApi.getId(), sellerApi);
        
        assertNotNull(sellerApi);
        assertEquals(sellerApi.getFirstName(), "Adam");
    }
    
    @Test
    public void shouldDeleteASeller() {
    	SellerApi sellerApi;
    	sellerApi = sellerService.searchByName("Adam");
    	sellerService.delete(sellerApi);
    	sellerApi = sellerService.searchByName("Adam");    	
    	assertNull(sellerApi);
    }
}
