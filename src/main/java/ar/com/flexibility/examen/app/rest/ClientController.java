package ar.com.flexibility.examen.app.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.exception.GenericException;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping(path = "/")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Client>> findAll() throws GenericException {
		return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Client> find(@PathVariable("id") Long id) throws GenericException {
		return new ResponseEntity<>(clientService.getById(id), HttpStatus.OK);
	}

	@PostMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Client> save(@Valid @NotNull @RequestBody Client client) throws GenericException {
		return new ResponseEntity<>(clientService.create(client), HttpStatus.CREATED);
	}

	@PutMapping(path = "{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Client> update(@PathVariable("id") Long id, @Valid @NotNull @RequestBody Client client)
			throws GenericException {
		client.setId(id);
		return new ResponseEntity<>(clientService.update(client), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Client> remove(@PathVariable("id") Long id) throws GenericException {
		clientService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}