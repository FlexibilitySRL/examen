package ar.com.flexibility.examen.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.ProcessClientService;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@RestController
@RequestMapping(path = "/clients")
public class ClientsController {
	
	private final Logger LOG = LoggerFactory.getLogger(ClientsController.class);
	
	@Autowired
	@Qualifier("ProcessMessageServiceImpl")
	private ProcessMessageService messageService;
	
	@Autowired
	@Qualifier("ProcessClientServiceImpl")
	private ProcessClientService clientService;
	
	@GetMapping("/get-client/{id}")
	public ResponseEntity<?> getClient(@PathVariable(value = "id") Long id){
		Client client = new Client();
		try {
			client = clientService.getClientById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);			
		}
		return ResponseEntity.ok().body(client);
	}
	
	@GetMapping("/get-all-client")
	public ResponseEntity<?> getAllClient(){
		List<Client> listclient = new ArrayList<>();
		try {
			listclient = clientService.getClients();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);			
		}
		return ResponseEntity.ok().body(listclient);
	}
	
	@PostMapping("/save-client")
	public ResponseEntity<?> saveClient(@RequestBody Client client) {
		Message message = new Message(); 
		Boolean resp = false;
		try {
			resp = clientService.saveClient(client);
			if(resp) {
				message = messageService.processMessage("Se ha guardando la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@PutMapping("/update-client/{id}")
	public ResponseEntity<?> updateClient(@PathVariable(value = "id") Long id, @RequestBody Client client) {
		Message message = new Message(); 
		Boolean resp = false;		
		try {
			resp = clientService.updateClient(client, id);
			if(resp) {
				message = messageService.processMessage("Se ha guardando la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-client/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id) {
		Message message = new Message(); 
		Boolean resp = false;		
		try {
			resp = clientService.deleteClient(id);
			if(resp) {
				message = messageService.processMessage("Se ha eliminado la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}						
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
	}

}
