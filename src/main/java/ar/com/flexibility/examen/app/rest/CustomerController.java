package ar.com.flexibility.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.rest.dto.CustomerRequestDto;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.CustomerService;
import ar.com.flexibility.examen.exception.DataValidationException;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/findCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> findCustomer(@RequestParam String customerName) { 
		return new ResponseEntity<Customer>(customerService.findCustomer(customerName), HttpStatus.OK);
	}
	
	@PostMapping(value = "/createCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCustomer(@RequestBody CustomerRequestDto dto) {
		customerService.createCustomer(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteCustomer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateCustomer(@RequestBody CustomerRequestDto dto) {
		customerService.updateCustomer(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
