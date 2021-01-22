package ar.com.plug.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping(path="/customers",produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllProduct() {
		return new ResponseEntity<>(service.getAllCustomer(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/customers/{customerid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteProduct(@PathVariable Long customerid) {
		service.deleteCustomer(customerid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/customers/{customerid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Customer> update(@PathVariable Long customerid) {
		return  new ResponseEntity<Customer>(service.getCustomerById(customerid), HttpStatus.OK);

	}

	@PostMapping(path = "/customers", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> saveProduct(@RequestBody Customer customer) {
		return  new ResponseEntity<Customer>(service.saveOrUpdate(customer), HttpStatus.OK);
	}

	@PutMapping(path = "/customers", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> update(@RequestBody Customer customer) {
		return new ResponseEntity<Customer>(service.saveOrUpdate(customer), HttpStatus.OK);

	}
	
}
