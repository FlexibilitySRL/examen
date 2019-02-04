package ar.com.flexibility.examen.app.rest;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.impl.ProductoServiceImpl;
import ar.com.flexibility.examen.utils.Utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@RestController
@ComponentScan(basePackages={"ar.com.flexibility.examen.*"})
@RequestMapping("/productos")
public class ProductoController {

	    @Autowired
	    ProductoServiceImpl productoService;
	    
	    private Logger logger = LogManager.getLogger(VendedorController.class);
	    
	    // Lista todos los producto
	    @GetMapping("/listar")
	    public ArrayList<Producto> getAllProductos() {
	    	ArrayList<Producto> list = new ArrayList<>();
			Iterable<Producto> agendas = productoService.findAll();
			agendas.forEach(list::add);
			logger.info("Listado de Productos - EJECUCION EXITOSA");
			return list;
	    }
	    
	    // Crea un nuevo producto
	    @PostMapping("/crear")
	    public ResponseEntity<?> createProducto(@Valid @RequestBody Producto producto) {
	    Producto productoRepetido = productoService.findByIdProducto(producto.getIdProducto());
	    	if (producto != null) {
	    		if (productoRepetido == null) {
		    		productoService.save(producto);
		    		logger.info("Creacion de Productos - EJECUCION EXITOSA");
			    	return new ResponseEntity<>(Utils.customResponse("Producto creado correctamente.", true, false),
							HttpStatus.OK);
	    		}else {
	    			logger.info("Creacion de Productos - EJECUCION FALLIDA");
		    		return new ResponseEntity<>(Utils.customResponse("El producto que desea ingresar ya existe", false, true),
		    				HttpStatus.BAD_REQUEST);
		    	}
	    	}else {
	    		logger.info("Creacion de Productos - EJECUCION FALLIDA");
	    		return new ResponseEntity<>(Utils.customResponse("Ingreso de datos incorrecto, por favor verifique de nuevo", false, true),
	    				HttpStatus.BAD_REQUEST);
	    	}
	    }

	    // Modifica el producto
	    @PostMapping("/modificar/{idProducto}")
	    public ResponseEntity<?> updateProducto(@PathVariable(value = "idProducto") Integer idProducto,
	                                            @Valid @RequestBody Producto productoDetails) {
	    	Producto producto = productoService.findByIdProducto(idProducto);
	    	 if (producto != null) {
	    		producto.setFechaVencimiento(Long.valueOf(productoDetails.getFechaVencimiento()));
	    		producto.setMarca(productoDetails.getMarca());
	    		producto.setNombre(productoDetails.getNombre());
		    	productoService.save(producto);
	    		logger.info("Modificacion de productos - EJECUCION EXITOSA");
		    	return new ResponseEntity<>(Utils.customResponse("Producto modificado correctamente.", true, false),
						HttpStatus.OK);
	    	 }else {
	    		logger.info("Modificacion de productos - EJECUCION FALLIDA");
	    		return new ResponseEntity<>(Utils.customResponse("El Producto que esta intentando modificar no existe en la base de datos.", false, true),
	    				HttpStatus.BAD_REQUEST);
	    	 }
	        
	    }
	    // Borra un producto
	    @DeleteMapping("/eliminar/{idProducto}")
	    public ResponseEntity<?> deleteProducto(@PathVariable(value = "idProducto") Integer idProducto) {
	    	Producto producto = productoService.findByIdProducto(idProducto);
	        if (producto != null) {
	        	productoService.removeByIdProducto(producto.getIdProducto());
				logger.info("Eliminacion de Productos - EJECUCION EXITOSA");
				return new ResponseEntity<>(Utils.customResponse("Se eliminó exitosamente el producto", true, false),
						HttpStatus.OK);
			} else {
				logger.info("Eliminacion de Producto - EJECUCION FALLIDA SE INTENTA ELIMINAR UN PRODUCTO INEXISTENTE");
				return new ResponseEntity<>(
						Utils.customResponse("El Producto que esta intentando eliminar no existe en la base de datos.", false, true), 
						HttpStatus.BAD_REQUEST);
			}
	    }
}
