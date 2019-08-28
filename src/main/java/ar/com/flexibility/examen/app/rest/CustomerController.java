package ar.com.flexibility.examen.app.rest;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import ar.com.flexibility.examen.app.api.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	// Get all customers
	@GetMapping("/all")
	public ResponseEntity<Iterable<Customer>> getAllCustomers() {
		Iterable<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	// Get paged customers ?page=0&size=1
	@GetMapping
	public ResponseEntity<Page<Customer>> getCustomers(Pageable pageable) {
		Page<Customer> customers = customerService.getCustomers(pageable);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	// Get a customer by id
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId)
			throws EntityNotFoundException {
		Customer customer = customerService.getCustomerById(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	// Get a customer by email
	@GetMapping("/search/findByEmail")
	public ResponseEntity<Customer> getCustomerByEmail(@Param("email") String email) throws EntityNotFoundException {
		Customer customer = customerService.getCustomerByEmail(email);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	// Create a new customer
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws EntityExistsException {
		Customer createdCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	}

	// Update a customer
	@PatchMapping(path = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO,
												   @PathVariable("customerId") Long customerId)
			throws EntityNotFoundException, EntityExistsException {
		Customer updatedCustomer = customerService.updateCustomer(customerDTO, customerId);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

	// Update the customer email
	@PatchMapping(path = "/{customerId}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Customer> updateCustomerEmail(@Valid @RequestBody String email,
														@PathVariable("customerId") Long customerId)
			throws EntityNotFoundException, EntityExistsException {
		Customer updatedCustomer = customerService.updateCustomerEmail(email, customerId);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

}
