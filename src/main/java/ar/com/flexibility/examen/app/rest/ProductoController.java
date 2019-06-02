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

import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.repo.IProductoRepo;

@RestController
@RequestMapping("/productos")
public class ProductoController{
	@Autowired
	private IProductoRepo repo;
	
	@GetMapping
	public List<Producto> listar(){
		return repo.findAll();
	}
	
	@PostMapping
	public void alta(@RequestBody Producto prod) {
		repo.save(prod);
	}
	
	@PutMapping
	public void modificar(@RequestBody Producto prod) {
		repo.save(prod);
	}
	
	@DeleteMapping(value = "/{id}")
	public void baja(@PathVariable("id") Integer id) {
		repo.delete(id);
	}	
}
