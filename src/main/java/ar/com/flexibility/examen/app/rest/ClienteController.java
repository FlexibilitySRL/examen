package ar.com.flexibility.examen.app.rest;

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
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.repository.IClienteRepo;
import ar.com.flexibility.examen.domain.service.impl.ClienteImpl;




@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	Logger LOG = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("servicioCliente")
	private  ClienteImpl clienteImpl;
	
	
	//Lista clientes
	@GetMapping 
	public List<Cliente> listar(){
		return clienteImpl.findAll();		
	}
	
	//Inserta clientes
	@PostMapping
	public ResponseEntity<Cliente> insertar (@RequestBody Cliente cliente) {
		clienteImpl.insertar(cliente);
		LOG.info("El cliente se ha agregado correctamente");	
		return new ResponseEntity<Cliente>(HttpStatus.ACCEPTED);
	}

	//Modifica clientes
	@PutMapping
	public ResponseEntity<Cliente> modificar(@RequestBody Cliente cliente) {
		clienteImpl.modificar(cliente);
		LOG.info("El cliente se ha modificado correctamente");
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
	
	//Elimina clientes
	@DeleteMapping(value = "/{nombre}")
	public ResponseEntity<Cliente> eliminar(@PathVariable("nombre") String nombre) {
		clienteImpl.eliminar(nombre);
		LOG.info("El cliente se ha eliminado correctamente");
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}

}
