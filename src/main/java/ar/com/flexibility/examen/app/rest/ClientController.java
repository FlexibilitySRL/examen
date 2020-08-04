package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    // ---------------
    // Attributes
    // ---------------
    @Autowired
    private MessagesProps messages;
	@Autowired
	private ClientService clientService;

    // ---------------
    // Methods
    // ---------------
	@DeleteMapping ("/{identifier}")
	public ResponseEntity<?> deleteClient (@PathVariable String identifier) {
		try {
			// To delete/remove a client
			this.clientService.delete (identifier);
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping ("/{identifier}")
	public ResponseEntity<?> getClient (@PathVariable String identifier) {
		try {
			// To get a client by its identifier
			Client client = this.clientService.get (identifier);
			
			return new ResponseEntity<>(client, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> list () {
		try {
			// To get a list of clients
			List<Client> data = this.clientService.list ();
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> newClient (@RequestBody ClientApi client) {
		try {
			// To save a new client
			this.clientService.save (client.getIdentifier(), 
					client.getName(), client.getSurname());
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping ("/{identifier}")
	public ResponseEntity<?> updateClient (@RequestBody ClientApi client, @PathVariable String identifier) {
		try {
			// To update a client
			this.clientService.update (identifier, client.getIdentifier(), 
					client.getName(), client.getSurname());
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
