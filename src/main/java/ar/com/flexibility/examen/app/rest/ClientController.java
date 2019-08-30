package ar.com.flexibility.examen.app.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;

@RestController
@RequestMapping(path = "/rest")
public class ClientController {

	private final Logger log = LoggerFactory.getLogger(ClientController.class);
	//@Autowired
	private ClientService service;

	public ClientController(ClientService service) {
		this.service = service;
	}

	/**
	 * GET /clients/id obtiene el cliente indicado por id
	 * @param id del cliente a buscar
	 * @return ResponseEntity con status 200 si el cliente existe o 404 si no
	 */
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> getClient(@PathVariable Long id) {
		log.debug("Request para obtener cliente : {}", id);

		Client client = service.findById(id);

		if (client == null) {
			log.debug("no existe id: {}", id);
			return new ResponseEntity<String>("El cliente no existe", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ClientApi>(toApi(client), HttpStatus.OK);
	}

	/**
	 * DELETE  /clients/id borra el cliente indicado por id.
	 * @param id del cliente a borrar
	 * @return ResponseEntity with status 204 
	 */
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		log.debug("Request para borrar cliente: {}", id);
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * POST /clients crea cliente nuevo
	 * @param clientApi
	 * @return ResponseEntity con status 201
	 * @throws URISyntaxException
	 */
	@PostMapping("/clients")
	public ResponseEntity<ClientApi> createClient(@Valid @RequestBody ClientApi clientApi) throws URISyntaxException {
		log.debug("Request para crear cliente : {}", clientApi);
		Client result = service.create(toEntity(clientApi));
		return ResponseEntity.created(new URI("/api/clients/" + result.getDni()))
				.body(toApi(result));
	}

	/**
	 * PUT  /clients actualiza un cliente existente
	 *
	 * @param clientApi el cliente a actualizar
	 * @return ResponseEntity con status 200 (OK) o
	 *         con status 404 si el clienteAip no tiene id valido,
	 * 
	 */
	@PutMapping("/clients")
	public ResponseEntity<?> updateClient(@Valid @RequestBody ClientApi clientApi) throws URISyntaxException {
		log.debug("Request para actualizar cliente : {}", clientApi);

		if (clientApi.getDni() == null) {
			log.error("el cliente no existe");
			return new ResponseEntity<String>("El cliente no existe", HttpStatus.NOT_FOUND);
		}

		Client result = null;
		try {
			result = service.update(toEntity(clientApi));
		}catch (Exception e)
		{
			log.error("el cliente no existe");
			return new ResponseEntity<String>("El cliente no existe", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok().body(toApi(result));
	}

	/**
	 * GET  /clients obtiene todos los clientes
	 * @return ResponseEntity con status 200
	 */
	@GetMapping("/clients")
	public ResponseEntity<List<ClientApi>> getAllClients(){
		log.info("Request para obtener todos los clientes");

		List<Client> clients = service.findAll();
		List<ClientApi> lista = new ArrayList<ClientApi>();
		for (Client product : clients)
		{
			lista.add(toApi(product));
		}

		return new ResponseEntity<List<ClientApi>>(lista, HttpStatus.OK);
	}

	private Client toEntity(ClientApi clientApi) {

		Client client = new Client();
		client.setDni(clientApi.getDni());
		client.setName(clientApi.getName());
		client.setEmail(clientApi.getEmail());
		return client;
	}

	private ClientApi toApi(Client client) {
		ClientApi clientApi = new ClientApi();
		if (client != null) {
			clientApi.setDni(client.getDni());
			clientApi.setName(client.getName());
			clientApi.setEmail(client.getEmail());
		}
		return clientApi;
	} 
}
