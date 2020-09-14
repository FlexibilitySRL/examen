package ar.com.flexibility.examen.app.rest;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

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

import ar.com.flexibility.examen.domain.model.Compra;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.CompraInterface;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;

@RestController
@RequestMapping(path = "/compras")
public class ComprasController {

	@Inject
	private CompraInterface compraInterface;
	
	@Inject
    private ProcessMessageService messageService;
	
	/*OBTIENE LAS COMPRAS REGISTRADAS EN MEMORIA*/
	@GetMapping
    public List<Compra> consultaCompras(){
    	return compraInterface.obtenerCompras();
    }
    
	/*REGISTRAR UNA COMPRA*/
    @PutMapping
    public ResponseEntity<Message> insertar(@RequestBody Compra compra) {
    	boolean resultado = compraInterface.crear(compra);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Compra Creado Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Creando Compra."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*BORRAR UNA COMPRA*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> eliminar(@PathVariable("id") long id) {
    	boolean resultado = compraInterface.eliminar(id);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Compra Eliminada Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Eliminando Compra."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*MODIFICCAR UNA COMPRA*/
    @PostMapping
    public ResponseEntity<Message> modificar(@RequestBody Compra compra) {
    	boolean resultado = compraInterface.modificar(compra);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Compra Modificada Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Modificando Compra."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*OBTENER DATOS DE UNA COMPRA*/
    @GetMapping("/compra/{id}")
    public Compra obtenerCompra(@PathVariable("id") Long idCompra) {
    	return compraInterface.obtenerCompra(idCompra);
    }
    
    /*OBTENER COMPRA DE CLIENTE*/
    @GetMapping("/compracliente/{idCliente}")
    public List<Compra> obtenerCompraCliente(@PathVariable("idCliente")Long idCliente){
    	return this.compraInterface.obtenerCompraCliente(idCliente);
    }
    
    /*AUTORIZAR COMPRA POR EL ID*/
    @GetMapping("/autorizar/{idCompra}")
    public ResponseEntity<Message> autorizarCompra(@PathVariable("idCompra") Long idCompra){
    	if(this.compraInterface.autorizarCompra(idCompra)) {
    		return new ResponseEntity<Message>(messageService.processMessage("Compra Modificada Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Modificando Compra."), HttpStatus.BAD_REQUEST);
    	}
    }
}
