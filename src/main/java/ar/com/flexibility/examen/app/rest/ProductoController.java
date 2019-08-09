package ar.com.flexibility.examen.app.rest;

import java.net.URI;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ar.com.flexibility.examen.app.api.ProductoApi;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.ProductoService;

@RestController
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	private static final Log log = LogFactory.getLog(ProductoController.class);
	
	@RequestMapping("/productos/{id}")
	public ResponseEntity<?> producto(@PathVariable("id") long id) {
		
		log.info("Get producto " + id);
		
		Producto producto = productoService.findById(id);
		if (producto == null) {
			return new ResponseEntity<Producto>(producto, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> updateProducto(@PathVariable("id") long id, @RequestBody ProductoApi productoApi) {
		
		log.info("Update producto " + id);
		
		Producto p = productoService.findById(id);
		if (p == null) {
			log.error("producto " + id + " no encontrado");
			return new ResponseEntity<Producto>(p, HttpStatus.NOT_FOUND);
		}
		
		// actualizar el producto
		p.setNombre(productoApi.getNombre());
		
		productoService.update(p);
		
		return new ResponseEntity<Producto>(p, HttpStatus.OK);
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable("id") long id) {
		
		log.info("Delete producto " + id);
		
		// me fijo si existe
		Producto producto = productoService.findById(id);
		if (producto == null) {
			log.error("Delete - producto " + id + " no encontrado");
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}

		productoService.deleteById(id);
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> createProducto(@RequestBody ProductoApi producto) {
		
		log.info("Create producto, " + "nombre: " + producto.getNombre());
		
		// completo el producto con los datos del post
		Producto p = new Producto(producto.getNombre());
		Producto newProducto = productoService.save(p);

		log.info("producto creado. id: " + newProducto.getId() + " nombre: " + newProducto.getNombre());
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newProducto.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	/*
	 * obtiene todos los productos
	 */
	@RequestMapping("/productos")
	public ResponseEntity<?> productos() {
		
		log.info("Get de todos los productos");
		
		List<Producto> productos = productoService.getAll();
		
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	
}
