package ar.com.flexibility.examen.app.rest;

import java.util.List;
import java.util.logging.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.repository.IClienteRepo;




@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	Logger LOG = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  IClienteRepo clienteRepo;
	
	
	//Lista clientes
	@GetMapping 
	public List<Cliente> listar(){
		return clienteRepo.findAll();		
	}
	
	//Inserta clientes
	@PostMapping
	public ResponseEntity<Cliente> insertar (@RequestBody Cliente cliente) {
		clienteRepo.save(cliente);
		LOG.info("El cliente se ha agregado correctamente");	
		return new ResponseEntity<Cliente>(HttpStatus.ACCEPTED);
	}

	//Modifica clientes
	@PutMapping
	public ResponseEntity<Cliente> modificar(@RequestBody Cliente cliente) {
		clienteRepo.save(cliente);
		LOG.info("El cliente se ha modificado correctamente");
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
	
	//Elimina clientes
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> eliminar(@PathVariable("id") Integer id) {
		clienteRepo.delete(id);
		LOG.info("El cliente se ha eliminado correctamente");
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}

}
