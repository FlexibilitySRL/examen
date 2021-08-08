package ar.com.plug.examen.app.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.dto.ProductoAltaDto;
import ar.com.plug.examen.domain.dto.ProductoUpdateDto;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.ProductoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(path = "/productos")
@RestController
@Validated
public class ProductoController {
	
	private static final Logger LOGGER = LogManager.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@ApiOperation(value = "Permite dar de alta un nuevo producto")
	@ApiResponses({ @ApiResponse(code = 202, message = "Created"), @ApiResponse(code = 400, message = "Los datos ingresados no son válidos") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> insert(@NotNull @Valid @RequestBody ProductoAltaDto request) {
		ResponseEntity<Producto> response = ResponseEntity.badRequest().build();
		try {
			Producto createdProduct = productoService.createProduct(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(createdProduct);
			LOGGER.info("Se creó exitosamente el producto con id: " + createdProduct.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar dar de alta un producto", e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite actualizar un producto")
	@ApiResponses({ @ApiResponse(code = 202, message = "Updated") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> update(@Valid @RequestBody ProductoUpdateDto request) {
		ResponseEntity<Producto> response = ResponseEntity.noContent().build();
		try {
			Producto updatedProduct = productoService.updateProduct(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedProduct);
			LOGGER.info("Se actualizó exitosamente el producto con id: " + updatedProduct.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar actualizar el producto con id: " + request.getId(), e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite eliminar un producto")
	@ApiResponses({ @ApiResponse(code = 200, message = "Borrado correcto"),  @ApiResponse(code = 404, message = "El registro que intenta eliminar no existe")})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@NotNull @PathVariable Integer id) {
		ResponseEntity<Void> result = ResponseEntity.ok().build();
		try {
			productoService.deleteProduct(id);
			LOGGER.info("Se eliminó exitosamente el producto con id: " + id);
		} catch (Exception e) {
			result = ResponseEntity.notFound().build();
			LOGGER.error("Ocurrió un error al intentar eliminar el producto con id: " + id, e);
		}
		return result;
	}
	
	@ApiOperation(value = "Obtiene todos lo productos registrados en el sistema")
	@GetMapping()
	public ResponseEntity<List<Producto>> findAll(){
		return ResponseEntity.ok(productoService.getProducts());
	}
	
	@ApiOperation(value = "Obtiene un producto por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> getMandanteById(@PathVariable Integer id) {
		ResponseEntity<Producto> response = ResponseEntity.notFound().build();
		Optional<Producto> optionalProduct = productoService.findById(id);
		if(optionalProduct.isPresent()) {
			response = ResponseEntity.ok(optionalProduct.get());
		}
		return response;
	}
	
}
