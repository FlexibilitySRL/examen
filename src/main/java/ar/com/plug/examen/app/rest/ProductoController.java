package ar.com.plug.examen.app.rest;

import java.util.List;

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

import ar.com.plug.examen.data.entity.Producto;
import ar.com.plug.examen.domain.dto.ProductoDTO;
import ar.com.plug.examen.domain.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping
	public  ResponseEntity<List<ProductoDTO>> listarProductos(){ 
		 
		return new ResponseEntity<>(this.productoService.listarProductos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> getClientById(@PathVariable long id)  {
		
		return new ResponseEntity<ProductoDTO>(this.productoService.consultarProductoPorId(id), HttpStatus.OK);
	}
	
	@PostMapping
	public  ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO){
		
		return new ResponseEntity<ProductoDTO>( this.productoService.crearProducto(productoDTO), HttpStatus.CREATED);
	}

	@PutMapping(path="/{id}")
	public  ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable("id") long id , @RequestBody  ProductoDTO productoDTO){
		return new ResponseEntity<ProductoDTO>( this.productoService.actualizarProducto(productoDTO),HttpStatus.OK);
	}

	@DeleteMapping(path="/{id}")
	public  ResponseEntity<Producto> eliminarCliente(@PathVariable("id") long id ){
		this.productoService.eliminarProducto(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
