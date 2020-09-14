package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.ClienteInterface;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;

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

@RestController
@RequestMapping(path = "/custom")
public class CustomController {

    @Autowired
    private ProcessMessageService messageService;
    
    @Autowired
    private ClienteInterface clienteInterface;
    
    @PostMapping("/echo")
    public ResponseEntity<?> echo(@RequestBody MessageApi message)
    {
        return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
    }
    
    /*OBTIENE LOS CLIENTES REGISTRADOS EN MEMORIA*/
    @GetMapping
    public List<Cliente> consultaClientes(){
    	return clienteInterface.obtenerClientes();
    }
    
    /*INSERTA UN CLIENTE*/
    @PutMapping
    public ResponseEntity<Message> insertar(@RequestBody Cliente cliente) {
    	boolean resultado = clienteInterface.crear(cliente);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Creado Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Creando Cliente."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*ELIMINA UN CLIENTE POR ID*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> eliminar(@PathVariable("id") long id) {
    	boolean resultado = clienteInterface.eliminar(id);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Eliminado Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Eliminando Cliente."), HttpStatus.BAD_REQUEST);
    	}
    }
    
    /*MODIFICA UN CLIENTE*/
    @PostMapping
    public ResponseEntity<Message> modificar(@RequestBody Cliente cliente) {
    	boolean resultado = clienteInterface.modificar(cliente);
    	if(resultado) {
    		return new ResponseEntity<Message>(messageService.processMessage("Modificado Correctamente."), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Message>(messageService.processMessage("Modificando Cliente."), HttpStatus.BAD_REQUEST);
    	}
    }
}
