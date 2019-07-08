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
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.impl.ProductoImpl;



@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	Logger LOG = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("servicio")
	private  ProductoImpl productoImpl;
	
	//Lista los productos
	@GetMapping ("/listar")
	public List<Producto> listar(){
		
		LOG.info("Lista de todos los productos");
		
		return productoImpl.obtenerLista();	
		
	}
	
	//Inserta productos
	@PostMapping
	public ResponseEntity<Producto> insertar(@RequestBody Producto producto) {
		productoImpl.insertar(producto);
		LOG.info("El producto se ha agregado correctamente");	
		return new ResponseEntity<Producto>(HttpStatus.ACCEPTED);
	}

	//Modifica Productos
	@PutMapping
	public ResponseEntity<Producto> modificar(@RequestBody Producto producto) {
		productoImpl.modificar(producto);
		LOG.info("El producto se ha modificado correctamente");
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}
	
	//Elimina Productos
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Producto> eliminar(@PathVariable("id") Integer id) {
		productoImpl.eliminar(id);
		LOG.info("El producto se ha eliminado correctamente");
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}

}
