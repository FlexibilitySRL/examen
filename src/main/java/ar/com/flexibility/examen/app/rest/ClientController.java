package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import io.swagger.annotations.ApiOperation;

@RestController

@RequestMapping(path = "/client")
public class ClientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	
    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Buscar clientes", response = Client.class)
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> getAllClients(){
    	LOGGER.info("Servicio de Busqueda de todos los clientes");
        return clientService.getClients();
    }
    
    @ApiOperation(value = "Buscar cliente por Id", response = Client.class)
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getClient(@PathVariable(value = "id") Long id){
    	LOGGER.info("Servicio de Busqueda de cliente por Id: {}", id);
        return clientService.getClientById(id);
    }
    
    @ApiOperation(value = "Insertar nuevos cliente", response = Client.class)
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> insertClient(@RequestBody Client client){
    	LOGGER.info("Servicio de Insercion de un nuevo cliente");
        return clientService.insertClient(client);
    }
    
    @ApiOperation(value = "Actualizar cliente", response = Client.class)
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateClient(@PathVariable(value = "id") Long id, @RequestBody Client client){
    	LOGGER.info("Servicio de actualizacion del cliente con id: {}", id);
        return clientService.updateClient(id, client);
    }
    
    @ApiOperation(value = "Borrar cliente", response = String.class)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id){
    	LOGGER.info("Servicio de Borrado de un cliente con id: {}", id);
        return clientService.deleteClient(id);
    }
    
}
