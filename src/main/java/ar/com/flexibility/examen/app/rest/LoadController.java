/**
 * 
 */
package ar.com.flexibility.examen.app.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.repository.SellerRepository;

/**
 * @author ro
 *
 */
@RestController
public class LoadController {
	
	@Autowired
	private SellerRepository sellerRepository;
	@Autowired
	private ClientRepository clientRepository;
	
	@RequestMapping(value = "loadData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void loadData(){
		Seller seller1 = new Seller();
	    Seller seller2 = new Seller();
		Product product1 =  new Product("Wood chairs",80D,seller1,10);
        Product product2 = new Product("Tables",50D,seller2,5);
       
	 	seller1.addProduct(product1);
	    seller2.addProduct(product2);
		sellerRepository.save(seller1);
		sellerRepository.save(seller2);
        
        Client client1 = new Client();
        Purchase purchase1 = new Purchase(client1, new Date(),200D);
        Order order1 = new Order(product1,6,purchase1);
        purchase1.addOrder(order1);
        
        client1.addPurchase(purchase1);
        clientRepository.save(client1); 
	}
}
