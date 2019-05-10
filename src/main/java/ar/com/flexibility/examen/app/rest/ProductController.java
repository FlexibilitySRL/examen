package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
@Api("Servcio de administracion de Productos")
public class ProductController
{
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@ApiOperation(value = "Obtiene todos los productos", response = ProductApi.class, responseContainer = "List")
	@ApiResponse(code = 200, message = "Obtención de Lista de Productos exitosa")
	@GetMapping(path = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll()
	{
		List<ProductApi> productsApi = new ArrayList<>();
		productService.findAll().forEach(product -> productsApi.add(new ProductApi(product)));

		log.info("Obtención de Lista de Productos exitosa");
		return new ResponseEntity<>(productsApi, HttpStatus.OK);
	}

	@ApiOperation(value = "Obtiene un Producto", response = ProductApi.class)
	@ApiResponse(code = 200, message = "Obtención del Producto éxitosa")
	@GetMapping(path = "{id:^[0-9]*$}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOne(
		@ApiParam(name = "id", value = "ID del Producto a obtener", required = true) 
		@PathVariable(value = "id", required = true) Long id)
	{
		try
		{
			Product product = productService.findOne(id);

			log.info("Obtención del Producto éxitosa");
			return new ResponseEntity<ProductApi>(new ProductApi(product), HttpStatus.OK);
		}
		catch (NotFoundException e)
		{
			log.info(e.getMessage());
			return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Crea un producto", response = ProductApi.class)
	@ApiResponse(code = 200, message = "Producto creado con éxito")
	@PostMapping(path = "add", produces = MediaType.APPLICATION_JSON_VALUE, 
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(
			@ApiParam(name = "Product", value = "Producto a crear", required = true) 
			@RequestBody ProductApi productApi)
	{
		Product product = productService.add(new Product(productApi));

		log.info("Producto creado con éxito");
		return new ResponseEntity<>(new ProductApi(product), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza un Producto", response = ProductApi.class)
	@ApiResponse(code = 200, message = "Producto actualizado con éxito")
	@PutMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE, 
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(
			@ApiParam(name = "Product", value = "Producto a actualizar", required = true) 
			@RequestBody ProductApi productApi)
	{
		try
		{
			Product productUpdated = productService.update(new Product(productApi));

			log.info("Producto actualizado con éxito");
			return new ResponseEntity<>(new ProductApi(productUpdated), HttpStatus.OK);
		}
		catch (NotFoundException e)
		{
			log.info(e.getMessage());
			return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		catch (GenericException e)
		{
			log.info(e.getMessage());
			return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@ApiOperation(value = "Elimina un Producto", response = MessageApi.class)
	@ApiResponse(code = 200, message = "Producto eliminado con éxito")
	@DeleteMapping(path = "delete/{id:^[0-9]*$}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(
			@ApiParam(name = "id", value = "ID del Producto a eliminar", required = true) 
			@PathVariable(value = "id", required = true) Long id)
	{
		try
		{
			productService.delete(id);

			log.info("Producto eliminado con éxito");
			return new ResponseEntity<>(new MessageApi("Producto eliminado con éxito"), HttpStatus.OK);
		}
		catch (NotFoundException e)
		{
			log.info(e.getMessage());
			return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
}
