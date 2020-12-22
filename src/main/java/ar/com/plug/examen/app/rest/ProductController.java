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

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;

/**
 * Controlador de la entidad Product.
 * @author epascuzzo
 *
 */
@RestController
@RequestMapping(path = "/products")
public class ProductController {
	@Autowired
	ProductService productService;

	/**
	 * Descripción: Método que devuelve todos los customers.
	 *
	 * HTTP VERB: GET
	 * URI: /payments/products
	 *  
	 * param   
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all() { 
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	/**
	 * Descripción: Método que crea un customer.
	 *
	 * HTTP VERB: POST
	 * URI: /payments/customers
	 *  
	 * param Product  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 201 ok
	 */
	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newProduct(@Valid @RequestBody Product product) {
		return new ResponseEntity<>(productService.add(product), HttpStatus.CREATED);
	}

	/**
	 * Descripción: Método que devuelve un product.
	 *
	 * HTTP VERB: GET
	 * URI: /payments/products/{id}
	 *  
	 * param long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@GetMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> one(@PathVariable long id) {
		return new ResponseEntity<>(productService.getOne(id), HttpStatus.OK);
	}

	/**
	 * Descripción: Método que actualiza un product.
	 *
	 * HTTP VERB: PUT
	 * URI: /payments/products
	 *  
	 * param Product  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@PutMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Product product) {
		return new ResponseEntity<>(productService.modify(product), HttpStatus.OK);
	}

	/**
	 * Descripción: Método que borra un product.
	 *
	 * HTTP VERB: DELETE
	 * URI: /payments/products/{id}
	 *  
	 * param long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@DeleteMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable long id) {
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
