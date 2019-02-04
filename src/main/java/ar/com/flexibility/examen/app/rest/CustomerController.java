package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.CustomerService;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
@RestController
@RequestMapping(path = "/private/customer")
public class CustomerController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerService customerService;

	
	/**
	 * Create a new customer
	 * 
	 * @param customer
	 * @return Customer
	 */
	@PostMapping
	@ApiOperation(value = "Create a new customer")
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		log.info("Creating a Customer");
		
		Customer response = customerService.create(customer);
		log.info("Customer created");
		
		return new ResponseEntity<Customer>(response, HttpStatus.OK);
	}
	
	/**
	 * Update a Customer
	 * 
	 * @param customer
	 * @return Customer
	 */
	@PutMapping
	@ApiOperation(value = "Update a Customer")
	public ResponseEntity<?> update(@RequestBody Customer customer) {
		log.info("Updating a Customer");
		
		Customer response = null;
		
		response = customerService.update(customer);
		
		if (response != null) {
			log.info("Customer updated");
			return new ResponseEntity<Customer>(response, HttpStatus.OK);
			
		} else {
			log.info("Customer don't exists");
			return new ResponseEntity<Message>(new Message("Customer don't exists"), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Get a Customer by ID
	 * 
	 * @param idCustomer
	 * @return Customer
	 */
	@GetMapping("/{idCustomer}")
	@ApiOperation(value = "Get a Customer by ID")
	public ResponseEntity<Customer> findOne(@PathVariable Long idCustomer) {
		log.info("Getting Customer by ID: "+idCustomer);
		
		Customer response = customerService.findById(idCustomer);
		
		return new ResponseEntity<Customer>(response, HttpStatus.OK);
	}
	
	/**
	 * Get All of Customers
	 * 
	 * @return List<Customer>
	 */
	@GetMapping
	@ApiOperation(value = "Get All of Customers")
	public ResponseEntity<List<Customer>> findAll() {
		log.info("Getting All of Customers");
		
		List<Customer> customers = customerService.findAll();
		
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	/**
	 * Delete a Customer by ID
	 * 
	 * @param idCustomer
	 */
	@DeleteMapping("/{idCustomer}")
	@ApiOperation(value = "Delete a Customer by ID")
	public void deleteById(@PathVariable Long idCustomer) {
		log.info("Deleting a Customer by ID: "+idCustomer);
		
		customerService.deleteById(idCustomer);
	}
	
}
