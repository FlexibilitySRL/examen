package ar.com.plug.examen.app.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import ar.com.plug.examen.domain.dto.ClienteAltaDto;
import ar.com.plug.examen.domain.dto.ClienteUpdateDto;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(path = "/clientes")
@RestController
public class ClienteController {
	
	private static final Logger LOGGER = LogManager.getLogger(ProductoController.class);
	
	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation(value = "Permite dar de alta un nuevo cliente")
	@ApiResponses({ @ApiResponse(code = 202, message = "Created"), @ApiResponse(code = 400, message = "Los datos ingresados no son válidos") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> insert(@NotNull @Valid @RequestBody ClienteAltaDto request) {
		ResponseEntity<Cliente> response = ResponseEntity.badRequest().build();
		try {
			Cliente createdClient = clienteService.createClient(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(createdClient);
			LOGGER.info("Se creó exitosamente el cliente con id: " + createdClient.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar dar de alta un cliente", e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite actualizar un cliente")
	@ApiResponses({ @ApiResponse(code = 202, message = "Updated") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> update(@NotNull @Valid @RequestBody ClienteUpdateDto request) {
		ResponseEntity<Cliente> response = ResponseEntity.noContent().build();
		try {
			Cliente updatedClient = clienteService.updateClient(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedClient);
			LOGGER.info("Se actualizó exitosamente el cliente con id: " + updatedClient.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar actualizar el cliente con id: " + request.getId(), e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite eliminar un cliente")
	@ApiResponses({ @ApiResponse(code = 200, message = "Borrado correcto"),  @ApiResponse(code = 404, message = "El registro que intenta eliminar no existe")})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@NotNull @PathVariable Integer id) {
		ResponseEntity<Void> result = ResponseEntity.ok().build();
		try {
			clienteService.deleteClient(id);
			LOGGER.info("Se eliminó exitosamente el cliente con id: " + id);
		} catch (Exception e) {
			result = ResponseEntity.notFound().build();
			LOGGER.error("Ocurrió un error al intentar eliminar el cliente con id: " + id, e);
		}
		return result;
	}
	
	@ApiOperation(value = "Obtiene todos lo clientes registrados en el sistema")
	@GetMapping()
	public ResponseEntity<List<Cliente>> findAll(){
		return ResponseEntity.ok(clienteService.getClients());
	}
	
	@ApiOperation(value = "Obtiene un cliente por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> getMandanteById(@PathVariable Integer id) {
		ResponseEntity<Cliente> response = ResponseEntity.notFound().build();
		Optional<Cliente> optionalClient = clienteService.findById(id);
		if(optionalClient.isPresent()) {
			response = ResponseEntity.ok(optionalClient.get());
		}
		return response;
	}
	

}
