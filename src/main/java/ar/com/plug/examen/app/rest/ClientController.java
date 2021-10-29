package ar.com.plug.examen.app.rest;

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

import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.dto.ClientDto;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

	@Autowired
	private ClientServiceImpl service;
	
	
	// Gets every stored client
	@GetMapping("/all")
	public ResponseEntity<List<ClientDto>> getClients(){
		return new ResponseEntity<List<ClientDto>>(service.findAll(), HttpStatus.OK);
	}
	
	
	// Gets a specific client from an id
	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> getClientsById(@PathVariable("id") Long id) throws Exception{
		return new ResponseEntity<ClientDto>(service.findById(id), HttpStatus.OK);
	}
	
	
	// Stores a new client
	@PostMapping("/create")
	public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto client){
		return new ResponseEntity<ClientDto>(service.save(client), HttpStatus.OK);
	}
	
	
	// Updates an existing client
	@PutMapping("/update")
	public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto client) throws Exception{
		return new ResponseEntity<ClientDto>(service.update(client), HttpStatus.OK);
	}
	
	
	// Deletes a specific client from an id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
