package ar.com.plug.examen.app.rest;

import javax.validation.Valid;

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

/**
 * Controlador de la entidad Customer.
 * @author epascuzzo
 *
 */

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	/**
	 * Descripción: Método que borra un customer.
	 *
	 * HTTP VERB: DELETE
	 * URI: /payments/customers/{id}
	 *  
	 * param long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all() { 
		return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
	}

	/**
	 * Descripción: Método que crea un customer.
	 *
	 * HTTP VERB: POST
	 * URI: /payments/customers
	 *  
	 * param Customer  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 201 ok
	 */
	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newCustomer(@Valid @RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.add(customer), HttpStatus.CREATED);

	}

	/**
	 * Descripción: Método que devuelve un customers.
	 *
	 * HTTP VERB: GET
	 * URI: /payments/customers/{id}
	 *  
	 * param long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@GetMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> one(@PathVariable long id) {
		return new ResponseEntity<>(customerService.getOne(id), HttpStatus.OK);
	}

	
	/**
	 * Descripción: Método que actualiza un customer.
	 *
	 * HTTP VERB: PUT
	 * URI: /payments/customers
	 *  
	 * param Customer  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@PutMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.modify(customer), HttpStatus.OK);
		
	}

	/**
	 * Descripción: Método que borra un customer.
	 *
	 * HTTP VERB: DELETE
	 * URI: /payments/customers/{id}
	 *  
	 * param long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@DeleteMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable long id) {
		customerService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
