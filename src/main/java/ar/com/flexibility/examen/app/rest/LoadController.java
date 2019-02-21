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
import ar.com.flexibility.examen.domain.repository.OrderRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
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
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	@RequestMapping(value = "loadData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void loadData(){
	
	    Seller seller1 = new Seller();
	    Seller seller2 = new Seller();
		sellerRepository.save(seller1);
		sellerRepository.save(seller2);
		
		Product product1 =  new Product("Wood chairs",80D,seller1,10);
        Product product2 = new Product("Tables",50D,seller2,5);
        productRepository.save(product1);
        productRepository.save(product2);
        
        Client client1 = new Client();
        clientRepository.save(client1);
        
        Purchase purchase1 = new Purchase(client1, new Date());
        purchaseRepository.save(purchase1);
        
        Order order1 = new Order(product1,6,purchase1);
        orderRepository.save(order1);
        
	}
}
