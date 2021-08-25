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

import ar.com.plug.examen.domain.dto.ClienteDTO;
import ar.com.plug.examen.domain.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public  ResponseEntity<List<ClienteDTO>> listarClientes()  { 
		
		return new ResponseEntity<>(this.clienteService.listarClientes(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getClientById(@PathVariable long id)  {
		
		return new ResponseEntity<ClienteDTO>(this.clienteService.consultarClientePorId(id), HttpStatus.OK);
	}

	@PostMapping
	public  ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO)  {		
		
		return new ResponseEntity<ClienteDTO>( this.clienteService.crearCliente(clienteDTO), HttpStatus.CREATED);
	}

	@PutMapping(path="/{id}")
	public  ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable("id") long id, @RequestBody ClienteDTO clienteDTO) throws ResourceNotFoundException{
	
		return new ResponseEntity<ClienteDTO>( this.clienteService.actualizarCliente(clienteDTO),HttpStatus.OK);
	}

	@DeleteMapping(path="/{id}")
	public  ResponseEntity<Object> eliminarCliente(@PathVariable("id") long id) {		
		this.clienteService.eliminarCliente(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
