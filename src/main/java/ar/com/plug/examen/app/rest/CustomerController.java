package ar.com.plug.examen.app.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;

	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all() { 
		logger.info("Get all customers.");
		return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
	}

	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> newCustomer(@Valid @RequestBody Customer customer) {
		logger.info("Creating new Customer with the following data: " + customer.toString());
		return new ResponseEntity<>(customerService.add(customer), HttpStatus.OK);

	}

	@GetMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> one(@PathVariable long id) {
		logger.info("Quering for the customer with id: " + id);
		return new ResponseEntity<>(customerService.getOne(id), HttpStatus.OK);
	}

	@PutMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> update(@RequestBody Customer customer) {
		logger.info("Updating Customer with the following data: " + customer.toString());
		return new ResponseEntity<>(customerService.modify(customer), HttpStatus.OK);
		
	}

	@DeleteMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> delete(@PathVariable long id) {
		customerService.delete(id);
		logger.info("Deleting Customer with id: " + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
