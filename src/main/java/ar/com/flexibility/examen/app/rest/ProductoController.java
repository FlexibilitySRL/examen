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
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.repository.IProductoRepo;



@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	Logger LOG = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  IProductoRepo productoRepo;
	
	//Lista los productos
	@GetMapping 
	public List<Producto> listar(){
		return productoRepo.findAll();		
	}
	
	//Inserta productos
	@PostMapping
	public ResponseEntity<Producto> insertar(@RequestBody Producto producto) {
		productoRepo.save(producto);
		LOG.info("El producto se ha agregado correctamente");	
		return new ResponseEntity<Producto>(HttpStatus.ACCEPTED);
	}

	//Modifica Productos
	@PutMapping
	public ResponseEntity<Producto> modificar(@RequestBody Producto producto) {
		productoRepo.save(producto);
		LOG.info("El producto se ha modificado correctamente");
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}
	
	//Elimina Productos
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Producto> eliminar(@PathVariable("id") Integer id) {
		productoRepo.delete(id);
		LOG.info("El producto se ha eliminado correctamente");
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}

}
