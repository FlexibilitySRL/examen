package ar.com.flexibility.examen.app.rest;

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

import ar.com.flexibility.examen.domain.model.Vendedor;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;
import ar.com.flexibility.examen.domain.service.VendedorInterface;

@RestController
@RequestMapping(path = "/vendedor")
public class VendedorController {
	
	@Autowired
    private ProcessMessageService messageService;
    
    @Autowired
    private VendedorInterface vendedorInterface;
    
    /*OBTIENE LOS VENDEDORES REGISTRADOS EN MEMORIA*/
    @GetMapping
    public List<Vendedor> consultaVendendores(){
    	return vendedorInterface.obtenerVendedores();
    }
    
    /*INSERTA UN VENDEDOR*/
    @PutMapping
    public ResponseEntity<Message> insertar(@RequestBody Vendedor vendedor) {
    	boolean resultado = vendedorInterface.crear(vendedor);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Vendedor Creado Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Creando Vendedor."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*ELIMINAR UN PRODUCTO POR ID*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> eliminar(@PathVariable("id") long id) {
    	boolean resultado = vendedorInterface.eliminar(id);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Eliminando Vendedor Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Eliminando Vendedor."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*MODIFICAR VENDEDOR*/
    @PostMapping
    public ResponseEntity<Message> modificar(@RequestBody Vendedor vendedor) {
    	boolean resultado = vendedorInterface.modificar(vendedor);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Vendedor Modificado Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Modificando Vendedor."), HttpStatus.BAD_REQUEST);
    	}
    }
}
