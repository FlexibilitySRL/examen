package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ar.com.flexibility.examen.app.api.RequestCliente;
import ar.com.flexibility.examen.domain.model.entity.Cliente;
import ar.com.flexibility.examen.domain.service.ProcessClienteService;

@RestController
@RequestMapping(path = "/rest/cliente")
public class ClienteController {

	private static final Logger LOGGER= LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ProcessClienteService clientesServices;

	@PostMapping("/echo")
	public ResponseEntity<?> echo(@RequestBody String message)
	{
		LOGGER.info("Se envio correctamente el mensaje echo");
		return new ResponseEntity<String>("OK!: " + message, HttpStatus.OK);
	}

	@PostMapping("/consultaTodos")
	public ResponseEntity<?> listar() throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String arrayToJson = mapper.writeValueAsString(clientesServices.findAll());

		LOGGER.info("Se solicito todos los clientes correctamente");
		return new ResponseEntity<String>(arrayToJson, HttpStatus.OK);
	}

	@PostMapping("/alta")
	public ResponseEntity<?> guardar(@RequestBody RequestCliente cliente)
	{
		if(cliente.getEmail() != "" && cliente.getNombre() != "" && cliente.getApellido() != "")
		{
		
			Cliente clienteEntity = new Cliente();
			clienteEntity.setApellido(cliente.getApellido());
			clienteEntity.setNombre(cliente.getNombre());
			clienteEntity.setEmail(cliente.getEmail());
			clientesServices.save(clienteEntity);	
		}
		else
		{
			LOGGER.error("Parte de la informacion ingresada para registrar un cliente esta vacia o es incorrecta");
			return new ResponseEntity<String>("Fallo!!!", HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("Se cargo correctamente los datos y se ha agregado el cliente nuevo exitosamente");
		return new ResponseEntity<String>("Agregado exitosamente!!!", HttpStatus.OK);
	}
	
	
	@DeleteMapping("/baja")
	public ResponseEntity<?> borrar(@RequestBody Long id)
	{
		if(id > 0) 
		{
			clientesServices.delete(id);
		}
		else 
		{
			LOGGER.error("El id de cliente ingresado es incorrecto y no se pudo borrar el cliente solicitado");
			return new ResponseEntity<String>("Fallo!!!", HttpStatus.BAD_REQUEST);
		}
		
		LOGGER.info("Se solicito un cliente por un id y fue borrado exitosamente");
		return new ResponseEntity<String>("Borrado exitoso!!!", HttpStatus.OK);
	}

	@PostMapping("/consultaUno")
	public ResponseEntity<?> consultaUno(@RequestBody Long id)
	{  
		if(id < 1)
		{
			LOGGER.error("El id de cliente ingresado es incorrecto y no se pudo encontrar el cliente solicitado");
			return new ResponseEntity<String>("Fallo!!!", HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("Se solicito un cliente por un id y fue encontrado exitosamente");
		return new ResponseEntity<Cliente>(clientesServices.findOne(id), HttpStatus.OK);
	}
}