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

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.repo.IClienteRepo;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private IClienteRepo repo;
	
	@GetMapping
	public List<Cliente> listar(){
		return repo.findAll();
	}
	
	@PostMapping
	public void alta(@RequestBody Cliente client) {
		repo.save(client);
	}
	
	@PutMapping
	public void modificar(@RequestBody Cliente client) {
		repo.save(client);
	}
	
	@DeleteMapping(value = "/{id}")
	public void baja(@PathVariable("id") Integer id) {
		repo.delete(id);
	}	
}