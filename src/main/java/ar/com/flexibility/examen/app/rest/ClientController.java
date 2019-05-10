package ar.com.flexibility.examen.app.rest;

import java.util.ArrayList;
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

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import javassist.NotFoundException;

@RestController
@RequestMapping(path = "/clients")
@Api("Servcio de administracion de Clientes")
public class ClientController
{
	private static final Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;

	@ApiOperation(value = "Obtiene todos los Clientes", response = ClientApi.class, responseContainer = "List")
	@ApiResponse(code = 200, message = "Obtención de Lista de Clientes exitosa")
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll()
	{
		List<ClientApi> clientsApi = new ArrayList<ClientApi>();
		clientService.findAll().forEach((client) -> clientsApi.add(new ClientApi(client)));
		
		log.info("Obtención de Lista de Clientes exitosa");
		return new ResponseEntity<>(clientsApi, HttpStatus.OK);
	}

	@ApiOperation(value = "Obtiene un Cliente", response = ClientApi.class)
	@ApiResponse(code = 200, message = "Obtención del Cliente éxitosa")
	@GetMapping(path = "/{id:^[0-9]*$}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOne(
			@ApiParam(name = "id", value = "ID del Cliente a obtener", required = true) @PathVariable(value = "id", required = true) Long id)
	{
		try
		{
			Client client = clientService.findOne(id);
			
			log.info("Obtención del Cliente éxitosa");
			return new ResponseEntity<>(new ClientApi(client), HttpStatus.OK);
		}
		catch (NotFoundException e)
		{
			log.info(e.getMessage());
			return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Crea un Cliente", response = ClientApi.class)
	@ApiResponse(code = 200, message = "Cliente creado con éxito")
	@PostMapping(path = "add", produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(
			@ApiParam(name = "Client", value = "Cliente a crear", required = true) 
			@RequestBody ClientApi clientApi)
	{
		Client client = clientService.add(new Client(clientApi));
		
		log.info("Cliente creado con éxito");
		return new ResponseEntity<>(new ClientApi(client), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza un Cliente", response = ClientApi.class)
	@ApiResponse(code = 200, message = "Cliente actualizado con éxito")
	@PutMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(
			@ApiParam(name = "Client", value = "Cliente a actualizar", required = true) 
			@RequestBody ClientApi clientApi)
	{
		try
		{
			Client client = clientService.update(new Client(clientApi));
			
			log.info("Cliente actualizado con éxito");
			return new ResponseEntity<>(new ClientApi(client), HttpStatus.OK);
		}
		catch (NotFoundException e)
		{
			log.info(e.getMessage());
			return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		catch (GenericException e)
		{
			log.info(e.getMessage());
			return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@ApiOperation(value = "Elimina un Cliente", response = MessageApi.class)
	@ApiResponse(code = 200, message = "Cliente eliminado con éxito")
	@DeleteMapping(path = "delete/{id:^[0-9]*$}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(
			@ApiParam(name = "id", value = "ID del Cliente a eliminar", required = true) 
			@PathVariable(value = "id", required = true) Long id)
	{
		try
		{
			clientService.delete(id);
			
			log.info("Cliente eliminado con éxito");
			return new ResponseEntity<>(new MessageApi("Cliente eliminado con éxito"), HttpStatus.OK);
		}
		catch (NotFoundException e)
		{
			log.info(e.getMessage());
			return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
}
