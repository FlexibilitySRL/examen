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

import ar.com.plug.examen.domain.model.Salesperson;
import ar.com.plug.examen.domain.service.SalespersonService;

/**
 * Controlador de la entidad Salesperson.
 * @author epascuzzo
 *
 */
@RestController
@RequestMapping(path = "/salespersons")
public class SalespersonController {
	@Autowired
	SalespersonService salespersonService;

	/**
	 * Descripción: Método que devuelve todos los salespersons.
	 *
	 * HTTP VERB: GET
	 * URI: /payments/salespersons
	 *  
	 * @param   
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all() { 
		return new ResponseEntity<>(salespersonService.getAll(), HttpStatus.OK);
	}

	/**
	 * Descripción: Método que crea un salesperson.
	 *
	 * HTTP VERB: POST
	 * URI: /payments/salespersons
	 *  
	 * @param Product  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 201 ok
	 */
	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newSalesperson(@Valid @RequestBody Salesperson salesperson) {
		return new ResponseEntity<>(salespersonService.add(salesperson), HttpStatus.CREATED);

	}

	/**
	 * Descripción: Método que devuelve un salesperson.
	 *
	 * HTTP VERB: GET
	 * URI: /payments/salespersons/{id}
	 *  
	 * @param long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */	
	@GetMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> one(@PathVariable long id) {
		return new ResponseEntity<>(salespersonService.getOne(id), HttpStatus.OK);
	}

	/**
	 * Descripción: Método que actualiza un salesperson.
	 *
	 * HTTP VERB: PUT
	 * URI: /payments/salespersons
	 *  
	 * @param Product  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */	
	@PutMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Salesperson salesperson) {
		return new ResponseEntity<>(salespersonService.modify(salesperson), HttpStatus.OK);
		
	}

	/**
	 * Descripción: Método que borra un salesperson.
	 *
	 * HTTP VERB: DELETE
	 * URI: /payments/salespersons/{id}
	 *  
	 * @param long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */	
	@DeleteMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable long id) {
		salespersonService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
