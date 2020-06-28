package ar.com.flexibility.examen.app.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.model.ShoppingCartProduct;
import ar.com.flexibility.examen.domain.model.ShoppingCartStatus;
import ar.com.flexibility.examen.domain.service.CustomerService;
import ar.com.flexibility.examen.domain.service.ShoppingCartService;

@Api(tags = "Shopping Cart API")
@RestController
@RequestMapping(path = "/shoppingCart")
public class ShoppingCartController {

	private final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	CustomerService customerService;
	
	/**
     * {@code POST  /addProductToCart} : Add product to shopping cart.
     *
     * @param product the product to be added.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new product,
     * or with status {@code 400 (Bad Request)} if the product has already an ID.
     */
    @PostMapping("/addProductToCart")
    @ApiOperation(value="Add a product to shopping cart",
	notes="Service used to add a product to shopping cart")
    public ResponseEntity<ShoppingCart> addProductToCart(@ApiParam("A shopping cart product") @Valid @RequestBody ShoppingCartProduct shoppingCartProduct,
    													 @ApiParam(value="Customer id", required = true) @PathVariable Long customerId) {
        
    	log.debug("REST request to add Product to shopping cart: {}", shoppingCartProduct);

    	if(!customerService.exists(customerId)){
    		throw new RuntimeException("Customer not exists!");
    	}
    	
    	Customer customer = customerService.getCustomerById(customerId);
    	
    	ShoppingCart shoppingCart = shoppingCartService.findByCustomerAndPendingState(customer);
    	
    	if (shoppingCart == null){
    		
    		shoppingCart = new ShoppingCart();
    		
    		shoppingCart.setShoppingCartStatus(ShoppingCartStatus.PENDING);
    		
    		shoppingCart.addProduct(shoppingCartProduct);
    		
    	}
    	
    	return ResponseEntity.ok(shoppingCartService.save(shoppingCart));
    }
    
    
    /**
     * {@code GET  /customer/{customerId}/pending} : Get Shopping cart pending for the customer.
     *
     * @param customerId the customer.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Shopping Cart
     * or with status {@code 404 (Bad Request)} if the product has already an ID.
     */
    @ApiOperation(value="Get Shopping Cart pending for the Customer",
	notes="Service used to get pending shopping cart for the customer")
    @GetMapping("/customer/{customerId}/pending")
    public ResponseEntity<ShoppingCart> getCustomerActiveShoppingCart(@PathVariable Long customerId) {
        if (!customerService.exists(customerId))
        	throw new RuntimeException("Customer not exists!");

        log.debug("REST request to get active shopping cart from customer: {}", customerId);
        
        Customer customer = customerService.getCustomerById(customerId);
        
        ShoppingCart shoppingCart = shoppingCartService.findByCustomerAndPendingState(customer);

        if (shoppingCart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(shoppingCart);
    }
    
    
    /**
     * {@code POST  /approve} : approve the "id" purchase.
     *
     * @param id the id of the purchaseDTO to approve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseDTO, or with status {@code 404 (Not Found)}.
     */
    @ApiOperation("Approve a shopping cart")
    @PostMapping("/approve/{id}")
    public ResponseEntity<ShoppingCart> approve(@ApiParam(value = "Shopping cart Id", required = true) @PathVariable Long id) {
    	
    	ShoppingCart shoppingCart = shoppingCartService.findById(id);
    	
    	shoppingCart.setShoppingCartStatus(ShoppingCartStatus.APPROVED);
    	
        return ResponseEntity.ok(shoppingCartService.save(shoppingCart));
    }
}
