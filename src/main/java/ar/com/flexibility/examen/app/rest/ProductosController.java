package ar.com.flexibility.examen.app.rest;

import java.util.List;

import javax.inject.Inject;

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

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.model.Vendedor;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;
import ar.com.flexibility.examen.domain.service.ProductoInterface;


@RestController
@RequestMapping(path = "/productos")
public class ProductosController {
	
	@Inject
	ProductoInterface productoInterface;
	
	@Autowired
    private ProcessMessageService messageService;
	
	/*OBTIENE PRODUCTOS REGISTRADOS EN MEMORIA*/
	@GetMapping
    public List<Producto> consultaProductos(){
    	return productoInterface.obtenerProductos();
    }
    
	/*INSERTA UN CLIENTE*/
    @PutMapping
    public ResponseEntity<Message> insertar(@RequestBody Producto producto) {
    	boolean resultado = productoInterface.crear(producto);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Producto Creado Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Creando Prodcuto."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*ELIMINA UN PRODUCTO POR ID*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> eliminar(@PathVariable("id") long id) {
    	boolean resultado = productoInterface.eliminar(id);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Eliminando producto Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Eliminando Producto."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*MODIFICA UN PRODUCTO*/
    @PostMapping
    public ResponseEntity<Message> modificar(@RequestBody Producto producto) {
    	boolean resultado = productoInterface.modificar(producto);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Producto Modificado Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Modificando Producto."), HttpStatus.BAD_REQUEST);
    	}
    }

}
