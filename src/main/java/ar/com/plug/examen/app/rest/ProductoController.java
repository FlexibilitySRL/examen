package ar.com.plug.examen.app.rest;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Productos;
import ar.com.plug.examen.domain.service.ProductoService;

@RestController
@RequestMapping("/Producto")
public class ProductoController {

	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	/**
          En este controlador encontramos El ABM de productos
	 */

	@PostMapping(path = "/saveProducto", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> saveProducto(@RequestBody Productos productos) {
		return new ResponseEntity<ResponseDTO>(productoService.saveProductos(productos), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteProducto")
	public ResponseEntity<ResponseDTO> deleteProducto(@Param(value = "id") Integer id) {
		return new ResponseEntity<ResponseDTO>(productoService.deleteProductos(id), HttpStatus.OK);
	}

	@PutMapping(path = "/updateProducto", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updateProducto(@RequestBody Productos productos) {
		return new ResponseEntity<ResponseDTO>(productoService.updateProductos(productos), HttpStatus.OK);
	}

}
