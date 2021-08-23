package ar.com.plug.examen.app.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.data.entity.Cliente;
import ar.com.plug.examen.data.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	public static final Logger logger = Logger.getLogger(ClienteController.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@GetMapping("/clientes")
	public  ResponseEntity<Iterable<Cliente>> listarClientes(){ 
		try {
			List<Cliente>  lista = new ArrayList<Cliente>();
			this.clienteRepository.findAll().forEach(lista::add);
			
			if (lista.isEmpty()) {
				logger.trace("La lista de clientes esta vacia.");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.trace("Se retorna(n) "+lista.size()+" registro(s).");
			return new ResponseEntity<>(lista, HttpStatus.OK);			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path="/clientes", produces = {MediaType.APPLICATION_JSON_VALUE },consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
		try {
			Cliente _cliente = this.clienteRepository.save(cliente);
			logger.trace("Registro Creado ID: "+cliente.getIdCliente()); 
			logger.trace(cliente.toString());
			return new ResponseEntity<>(_cliente, HttpStatus.CREATED);			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path="/clientes/{id}")
	public  ResponseEntity<Cliente> actualizarCliente(@PathVariable("id") long id , @RequestBody  Cliente cliente){
		try {
			Optional<Cliente> clienteInfo = this.clienteRepository.findById(id);
			logger.trace("Registro para actualizar ID: "+id); 
			if (clienteInfo.isPresent()) {
				logger.trace("Registro Existe");
				logger.trace("Registro Actualizado"+cliente.toString());
				Cliente _cliente = clienteInfo.get();
				_cliente.setNombre(cliente.getNombre());
				_cliente.setApellido(cliente.getApellido());
				_cliente.setDireccion(cliente.getDireccion());
				_cliente.setEmail(cliente.getEmail());
				_cliente.setTelefono(cliente.getTelefono());
				return new ResponseEntity<>(this.clienteRepository.save(_cliente), HttpStatus.OK);
			}else {
				logger.trace("Registro No Existe"); 
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path="/clientes/{id}")
	public  ResponseEntity<Cliente> eliminarCliente(@PathVariable("id") long id ){
		try {
			logger.trace("Registro para Eliminar ID: "+id); 
			this.clienteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		} catch (Exception e) {
			logger.trace("Registro No Existe"); 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
