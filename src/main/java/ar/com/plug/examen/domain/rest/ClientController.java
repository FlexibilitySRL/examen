package ar.com.plug.examen.domain.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.service.IClientRepo;
import ar.com.plug.examen.entities.Client;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private IClientRepo clientService;
	
	/**
	 * Return all clients
	 * @return List<ClientDTO>
	 */
	@GetMapping("/")
	public ResponseEntity<List<ClientDTO>> getClients() {
		return new ResponseEntity<>(this.clientService.findAll(), HttpStatus.OK);
	}
	
	
	/**
	 * Find one client by id
	 * @param id -> long
	 * @return ClientDTO
	 * @throws ResourceNotFoundError
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable long id) throws ResourceNotFoundError {
	    return new ResponseEntity<ClientDTO>(this.clientService.findClientById(id), HttpStatus.OK);
	}
	
	/**
	 * Save one client.
	 * @param request -> ClientDTO
	 * @return ClientDTO
	 * @throws BadRequestError 
	 */
	@PostMapping("/")
	public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO request) throws BadRequestError {
		return new ResponseEntity<ClientDTO>(this.clientService.save(request), HttpStatus.CREATED);
	}
	     
	/**
	 * Delete a client by id
	 * @param id -> long
	 * @return void
	 * @throws ResourceNotFoundError
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) throws ResourceNotFoundError {
		this.clientService.delete(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * Update a client
	 * @param request -> ClientDTO
	 * @return ClientDTO
	 * @throws ResourceNotFoundError
	 * @throws BadRequestError 
	 */
	@PutMapping("/")
	public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO request) throws ResourceNotFoundError, BadRequestError {
		return new ResponseEntity<ClientDTO>(this.clientService.update(request), HttpStatus.OK);
	}
	

}
