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

@RestController
@RequestMapping(path = "/operations")
public class OperationController {
	@Autowired
	OperationService operationService;

	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all() { 
		return new ResponseEntity<>(operationService.getAll(), HttpStatus.OK);
	}

	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> newOperation(@Valid @RequestBody Operation operation) {
		return new ResponseEntity<>(operationService.add(operation), HttpStatus.OK);

	}

	@GetMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> one(@PathVariable long id) {
		return new ResponseEntity<>(operationService.getOne(id), HttpStatus.OK);
	}
	
	@PutMapping(path = "{id}/aprove", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> aprove(@PathVariable long id) {
		return new ResponseEntity<>(operationService.aprove(id), HttpStatus.ACCEPTED);
	}
}
