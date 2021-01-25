package ar.com.plug.examen.app.rest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;
import lombok.Data;

@RestController
@Data
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping(path="/customers",produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllCustomers() {
		return new ResponseEntity<>(service.getAllCustomer(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/customers/{customerid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteProduct(@PathVariable Long customerid) {
		service.deleteCustomer(customerid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/customers/{customerid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerid) {
		return  new ResponseEntity<Customer>(service.getCustomerById(customerid), HttpStatus.OK);

	}

	@PostMapping(path = "/customers", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
		return  new ResponseEntity<Customer>(service.create(customer), HttpStatus.CREATED);
	}

	@PutMapping(path = "/customers", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer) {
		return new ResponseEntity<Customer>(service.update(customer), HttpStatus.OK);

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> "field " + x.getField() + " " + x.getDefaultMessage()).collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
}
