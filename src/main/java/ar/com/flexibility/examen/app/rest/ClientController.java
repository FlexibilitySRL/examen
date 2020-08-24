package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.service.ProcessClientService;

@RestController
@RequestMapping(path = "/client")
public class ClientController {
	
	private final Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ProcessClientService clientService;

	/**
	 * Client create operation
	 * @param clientApi
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ClientApi clientApi) {
		String mensaje = "";
		try {
			log.info("Adding a new client...");
			return new ResponseEntity<ClientApi>(clientService.create(clientApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error creating a client.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Client update operation
	 * @param clientId
	 * @param clientApi
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestParam("id") Long clientId, @RequestBody ClientApi clientApi) {
		String mensaje = "";
		log.info("Updating a client...");
		try {
			return new ResponseEntity<ClientApi>(clientService.update(clientId, clientApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error updating a client.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Client delete operation
	 * @param clientApi
	 * @return
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody ClientApi clientApi) {
		String mensaje = "";
		log.info("Deleting client...");
		try {
			return new ResponseEntity<String>(clientService.delete(clientApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error deleting the client.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
