package ar.com.plug.examen.app.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ServiceGeneric;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

	private final ServiceGeneric<ClientApi,Client> clientService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public ClientController(ClientServiceImpl clientService) {
		this.clientService = clientService;
	}

	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createClient(@RequestBody ClientApi clientApi) throws ValidatorException {
		clientService.create(clientApi);
		logger.info("Se creo el Cliente correctamente");
		return new ResponseEntity<>("Se creo el Cliente correctamente", HttpStatus.OK);
	}

	@PutMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateClient(@RequestBody ClientApi clientApi) throws ValidatorException, NotExistException {
		clientService.update(clientApi);
		logger.info("Se modifico el Cliente correctamente");

		return new ResponseEntity<>("Se modifico el Cliente correctament", HttpStatus.OK);
	}

	@GetMapping(path = "/{idClient}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientApi> findClient(@PathVariable Long idClient) throws NotExistException, ValidatorException {
		ClientApi client = clientService.find(idClient);
		logger.info("Se encontro el Cliente correctamente");

		return new ResponseEntity<>(client, HttpStatus.OK);
	}

	@GetMapping(path = "")
	public ResponseEntity<List<ClientApi>> findClient() throws NotExistException {
		List<ClientApi> clients = clientService.findAll();
		logger.info("Se encontroraon los Clientes correctamente");

		return new ResponseEntity<>(clients, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{idClient}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> deleteClient(@PathVariable Long idClient) throws NotExistException, ValidatorException {
		clientService.delete(idClient);
		logger.info("Se elimino el Cliente correctamente");

		return new ResponseEntity<>("Se elimino el Cliente correctamente", HttpStatus.OK);
	}
}
