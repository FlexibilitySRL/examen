package ar.com.flexibility.examen.app.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.repo.IClienteRepo;



@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private IClienteRepo repo;
	private static final Log logger = LogFactory.getLog(ClienteController.class);
	@GetMapping
	public List<Cliente> listar(){
		logger.info("Mostrando lista de clientes");
		try {
			return repo.findAll();
		}catch(Exception e) {
			logger.info("Error al Mostra la lista de clientes");
			return null;
		}
	}
	
	@PostMapping
	public void alta(@RequestBody Cliente client) {
		logger.info("Dando de alta al cliente" + client.getNombre());
		try {
			repo.save(client);
			logger.info("Dado de alta al cliente" + client.getNombre());
		}catch(Exception e) {
			logger.info("Error al dar de alta al cliente");
		}
	}
	
	@PutMapping
	public void modificar(@RequestBody Cliente client) {
		logger.info("Modificando cliente");
		try {
			repo.save(client);
			logger.info("Modificado cliente" + client.getNombre());
		}catch(Exception e) {
			logger.info("Error al modificar cliente");
		}
	}	
	
	@DeleteMapping(value = "/{id}")
	public void baja(@PathVariable("id") Integer id) {
		logger.info("Borrando cliente " + repo.findOne(id).getNombre() );
		try {
			repo.delete(id);
			logger.info("Cliente " + repo.findOne(id).getNombre() + " borrado");
		}catch(Exception e) {
			logger.info("Error al eliminar cliente");
		}
	}	
}