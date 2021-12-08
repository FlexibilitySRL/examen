package ar.com.plug.examen.domain.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.PurchaseRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PurchaseTest {
	
	@Autowired
    PurchaseRepository purchaseRepository;
    
    @Test
    public void testFindAllPurchase()
    {
    	Purchase purchase = new Purchase();
    	purchase.setId(23l);
    	purchase.setId_Product(2l);
    	purchase.setIdCompra(3l);
    	BigDecimal bdlFromInt = new BigDecimal(2500d);
    	purchase.setPrecio(bdlFromInt);
    	purchase.setUnidad(2);
    	 purchaseRepository.save(purchase);
    	 
    	    Iterable<Purchase> purchase2 = purchaseRepository.findAll();
    	    Assertions.assertThat(purchase2).extracting(Purchase::getIdCompra).containsOnly(3l);
    }

}
