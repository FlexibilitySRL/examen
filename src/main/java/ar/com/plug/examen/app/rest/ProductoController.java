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

import ar.com.plug.examen.data.entity.Producto;
import ar.com.plug.examen.data.repository.ProductoRepository;

@RestController
public class ProductoController {
	
	public static final Logger logger = Logger.getLogger(ProductoController.class);

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	public ProductoController(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@GetMapping("/productos")
	public  ResponseEntity<Iterable<Producto>> listarClientes(){ 
		try {
			List<Producto>  lista = new ArrayList<Producto>();
			this.productoRepository.findAll().forEach(lista::add);
			
			if (lista.isEmpty()) {
				logger.trace("La lista de productos esta vacia.");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.trace("Se retorna(n) "+lista.size()+" registro(s).");
			return new ResponseEntity<>(lista, HttpStatus.OK);			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path="/productos", produces = {MediaType.APPLICATION_JSON_VALUE },consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Producto> crearProducto(@RequestBody Producto producto){
		try {
			Producto _producto = this.productoRepository.save(producto);
			logger.trace("Registro Creado ID: "+producto.getIdProducto()); 
			logger.trace(producto.toString());
			return new ResponseEntity<>(_producto, HttpStatus.CREATED);			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path="/productos/{id}")
	public  ResponseEntity<Producto> actualizarProducto(@PathVariable("id") long id , @RequestBody  Producto producto){
		try {
			Optional<Producto> productoInfo = this.productoRepository.findById(id);
			logger.trace("Registro para actualizar ID: "+id); 
			if (productoInfo.isPresent()) {
				logger.trace("Registro Existe");
				logger.trace("Registro Actualizado"+producto.toString());
				Producto _producto = productoInfo.get();
				_producto.setDescripcion(producto.getDescripcion());
				_producto.setPrecio(producto.getPrecio());
				return new ResponseEntity<>(this.productoRepository.save(_producto), HttpStatus.OK);
			}else {
				logger.trace("Registro No Existe"); 
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path="/productos/{id}")
	public  ResponseEntity<Producto> eliminarCliente(@PathVariable("id") long id ){
		try {
			logger.trace("Registro para Eliminar ID: "+id); 
			this.productoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		} catch (Exception e) {
			logger.trace("Registro No Existe"); 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
