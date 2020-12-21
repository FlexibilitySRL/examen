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

@RestController
@RequestMapping(path = "/salespersons")
public class SalespersonController {
	@Autowired
	SalespersonService salespersonService;

	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all() { 
		return new ResponseEntity<>(salespersonService.getAll(), HttpStatus.OK);
	}

	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newSalesperson(@Valid @RequestBody Salesperson salesperson) {
		return new ResponseEntity<>(salespersonService.add(salesperson), HttpStatus.CREATED);

	}

	@GetMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> one(@PathVariable long id) {
		return new ResponseEntity<>(salespersonService.getOne(id), HttpStatus.OK);
	}

	@PutMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Salesperson salesperson) {
		return new ResponseEntity<>(salespersonService.modify(salesperson), HttpStatus.OK);
		
	}

	@DeleteMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable long id) {
		salespersonService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
