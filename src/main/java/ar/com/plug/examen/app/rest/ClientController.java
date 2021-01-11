package ar.com.plug.examen.app.rest;

import java.util.List;

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

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.service.ClientService;

@RestController
@RequestMapping(path="/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClientApi> createClient(@RequestBody ClientApi clientApi){
		
		return new ResponseEntity<>(clientService.createClient(clientApi), HttpStatus.CREATED);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ClientApi> getClient(@PathVariable Long id){
		
		return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<ClientApi>>  listAllClients(){
		
		return new ResponseEntity<>(clientService.listAllClients(), HttpStatus.OK);
		
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity removeClient(@PathVariable Long id) {
		
		clientService.removeClient(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientApi> updateClient(@PathVariable Long id, @RequestBody ClientApi clientApi){
		
		return new ResponseEntity<>(clientService.updateClient(id, clientApi), HttpStatus.CREATED);
		
	}
	
}

