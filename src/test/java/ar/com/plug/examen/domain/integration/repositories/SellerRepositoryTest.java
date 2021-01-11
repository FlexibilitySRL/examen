package ar.com.plug.examen.domain.integration.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repositories.SellerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SellerRepositoryTest {
	
	    @Autowired
	    private SellerRepository sellerRepository;
	    
	    
	    @Test
	    public void create() {
	    	Seller seller =new Seller();
	    	seller.setName("SellerTestName");
	        seller = sellerRepository.save(seller);
	        assertNotNull(seller.getId());
	    }

	    @Test
	    public void list() {
	        List<Seller> lists = sellerRepository.findAll();
	        assertNotNull(lists);
	        assertFalse(lists.isEmpty());
	    }
	    
	    @Test
	    public void getSeller() {
	    	Seller seller =new Seller();
	    	seller.setName("SellerTestName");
	        seller = sellerRepository.save(seller);
	    	
	        Seller retrieved = sellerRepository.findById(seller.getId()).orElse(null);
	        assertNotNull(retrieved);
	        assertNotNull(retrieved.getId());
	        assertEquals(seller.getId(), retrieved.getId());
	    }
	    
	    @Test
	    public void delete() {
	    	Seller seller =new Seller();
	    	seller.setName("SellerTestName");
	        seller = sellerRepository.save(seller);
	        
	        sellerRepository.deleteById(seller.getId());
	        
	        assertFalse(sellerRepository.findById(seller.getId()).isPresent());
	    	
	    }
	    
	    @Test
	    public void update() {
	    	
	    	Seller seller =new Seller();
	    	seller.setName("SellerTestName");
	        seller = sellerRepository.save(seller);
	        
	        seller.setName("SellerUpdateTest");
	        
	        Seller updated = sellerRepository.save(seller);
	        
	        assertNotNull(updated);
	        assertEquals(seller.getId(), updated.getId());
	        assertEquals(seller.getName(), updated.getName());
	    }
	    
}

















