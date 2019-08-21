package ar.com.flexibility.examen.app.rest;

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

import ar.com.flexibility.examen.domain.dto.ClientDTO;
import ar.com.flexibility.examen.domain.dto.LegalClientDTO;
import ar.com.flexibility.examen.domain.dto.NaturalClientDTO;
import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.dto.PageRequestDTO;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.service.ClientService;

@RestController
@RequestMapping(path = "/")
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	@GetMapping("legalclients")
	public ResponseEntity<List<ObjectDTO<LegalClientDTO>>> listLegalClients(@RequestBody PageRequestDTO pageRequestDTO) {
		return new ResponseEntity<List<ObjectDTO<LegalClientDTO>>>(this.clientService.listLegalClients(pageRequestDTO), HttpStatus.OK);
	}
	
	@GetMapping("naturalclients")
	public ResponseEntity<List<ObjectDTO<NaturalClientDTO>>> listNaturalClients(@RequestBody PageRequestDTO pageRequestDTO) {
		return new ResponseEntity<List<ObjectDTO<NaturalClientDTO>>>(this.clientService.listNaturalClients(pageRequestDTO), HttpStatus.OK);
	}
	
	@GetMapping("client/{id}")
	public ResponseEntity<ClientDTO> getClient(@PathVariable("id") Long id) {
		return new ResponseEntity<ClientDTO>(this.clientService.getClient(id), HttpStatus.OK);
	}
	
	@PostMapping("client")
	public ResponseEntity<Long> addClient(@RequestBody ClientDTO clientDTO) {
		return new ResponseEntity<Long>(this.clientService.addClient(clientDTO), HttpStatus.OK);
	}
	
	@PutMapping("client/{id}")
	public ResponseEntity<Void> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) {
		this.clientService.updateClient(id, clientDTO);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("client/{id}")
	public ResponseEntity<Void> removeClient(@PathVariable("id") Long id) {
		this.clientService.removeClient(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
