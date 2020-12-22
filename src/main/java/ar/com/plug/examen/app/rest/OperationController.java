package ar.com.plug.examen.app.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Operation;
import ar.com.plug.examen.domain.service.OperationService;

/**
 * Controlador de la entidad Operation.
 * @author epascuzzo
 *
 */
@RestController
@RequestMapping(path = "/operations")
public class OperationController {
	@Autowired
	OperationService operationService;

	/**
	 * Descripción: Método que devuelve todas las operation.
	 *
	 * HTTP VERB: GET
	 * URI: /payments/operations
	 *  
	 * @param   
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */	
	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all() { 
		return new ResponseEntity<>(operationService.getAll(), HttpStatus.OK);
	}

	/**
	 * Descripción: Método que crea una operation.
	 *
	 * HTTP VERB: POST
	 * URI: /payments/operations
	 *  
	 * @param Operation  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 201 ok
	 */	
	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newOperation(@Valid @RequestBody Operation operation) {
		return new ResponseEntity<>(operationService.add(operation), HttpStatus.CREATED);

	}

	/**
	 * Descripción: Método que devuelve un operation.
	 *
	 * HTTP VERB: GET
	 * URI: /payments/operations/{id}
	 *  
	 * @param Long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 200 ok
	 */
	@GetMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> one(@PathVariable long id) {
		return new ResponseEntity<>(operationService.getOne(id), HttpStatus.OK);
	}
	
	/**
	 * Descripción: Método aprueba una operation.
	 *
	 * HTTP VERB: PUT
	 * URI: /payments/operations/{id}/aprove
	 *  
	 * @param Long  
	 * @return ResponseEntity
	 * HTTP RESPONSE STATUS: 202 accepted
	 */	
	@PutMapping(path = "{id}/aprove", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> aprove(@PathVariable long id) {
		return new ResponseEntity<>(operationService.aprove(id), HttpStatus.ACCEPTED);
	}
}
