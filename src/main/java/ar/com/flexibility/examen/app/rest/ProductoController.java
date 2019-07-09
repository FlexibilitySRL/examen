package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ar.com.flexibility.examen.app.api.RequestProducto;
import ar.com.flexibility.examen.domain.model.entity.Producto;
import ar.com.flexibility.examen.domain.service.ProcessProductoService;

@RestController
@RequestMapping(path = "/rest/producto")
public class ProductoController {

	private static final Logger LOGGER= LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProcessProductoService productosService;
	
	@PostMapping("/echo")
	public ResponseEntity<?> echo(@RequestBody String message)
	{
		LOGGER.info("Se envio correctamente el mensaje echo");
		return new ResponseEntity<String>("OK!: " + message, HttpStatus.OK);
	}
	
	@PostMapping("/consultaTodos")
	public ResponseEntity<?> listar() throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String arrayToJson = mapper.writeValueAsString(productosService.findAll());
		
		LOGGER.info("Se solicito todos los productos correctamente");
		return new ResponseEntity<String>(arrayToJson, HttpStatus.OK);
	}
	
	@PostMapping("/alta")
	public ResponseEntity<?> guardar(@RequestBody RequestProducto producto)
	{
		if(producto.getDescripcion() != "" && producto.getNombre() != "" && producto.getPrecio() > 0)
		{	
			Producto productoEntity = new Producto();
			productoEntity.setDescripcion(producto.getDescripcion());
			productoEntity.setNombre(producto.getNombre());
			productoEntity.setPrecio(producto.getPrecio());
			productosService.save(productoEntity);
		}
		else
		{
			LOGGER.error("Parte de la informacion ingresada para registrar un producto esta vacia o es incorrecta");
			return new ResponseEntity<String>("Fallo!!!", HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("Se cargo correctamente los datos y se ha agregado el producto nuevo exitosamente");
		return new ResponseEntity<String>("Agregado exitosamente!!!", HttpStatus.OK);
	}
	
	@DeleteMapping("/baja")
	public ResponseEntity<?> borrar(@RequestBody Long id)
	{
		if(id > 0)
		{
			productosService.delete(id);
		}
		else 
		{
			LOGGER.error("El id de producto ingresado es incorrecto y no se pudo borrar el producto solicitado");
			return new ResponseEntity<String>("Fallo!!!", HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("Se solicito un producto por un id y fue borrado exitosamente");
		return new ResponseEntity<String>("Borrado exitoso!!!", HttpStatus.OK);
	}
	
	@PostMapping("/consultaUno")
	public ResponseEntity<?> consultaUno(@RequestBody Long id)
	{  
		if(id < 1)
		{
			LOGGER.error("El id de producto ingresado es incorrecto y no se pudo encontrar el producto solicitado");
			return new ResponseEntity<String>("Fallo!!!", HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("Se solicito un vendedor por un id y fue encontrado exitosamente");
		return new ResponseEntity<Producto>(productosService.findOne(id), HttpStatus.OK);
	}
}
