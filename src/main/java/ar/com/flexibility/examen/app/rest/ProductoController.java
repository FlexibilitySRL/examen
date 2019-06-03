package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	private static final Log logger = LogFactory.getLog(ProductoController.class);
	
	@Autowired
	private IProductoRepo repo;
	
	@GetMapping
	public List<Producto> listar(){
		try {
			logger.info("Mostrando lista de productos");
			return repo.findAll();
		}catch(Exception e) {
			logger.info("Error al mostrar la lista de productos");
			return null;
		}	
	}
	
	@PostMapping

	public void alta(@RequestBody Producto prod) {
		logger.info("Dando de alta al producto " + prod.getNombre());
		try {
			repo.save(prod);
			logger.info("Dando de alta al producto " + prod.getNombre());
		}catch(Exception e) {
			logger.info("Error al dar de alta el producto");
		}
	}
	
	@PutMapping
	public void modificar(@RequestBody Producto prod) {
		logger.info("Modificando producto " + prod.getNombre());
		try{
			repo.save(prod);
			logger.info("Modificado producto " + prod.getNombre());
		}catch(Exception e) {
			logger.info("Error al modificar el producto");
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public void baja(@PathVariable("id") Integer id) {
		logger.info("Dando de Baja producto " + repo.findOne(id).getNombre());
		try{
			repo.delete(id);
			logger.info("Producto " + repo.findOne(id).getNombre() + " dado de baja");
		}catch(Exception e) {
			logger.info("Error al dar de baja el producto");
		}
	}	
}
