package ar.com.plug.examen.app.rest.controller;

import ar.com.plug.examen.app.rest.controller.docs.ClienteControllerDoc;
import ar.com.plug.examen.domain.model.dto.ClienteRestDto;
import ar.com.plug.examen.domain.service.IClienteService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController implements ClienteControllerDoc  {

	private static final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private IClienteService clienteService;
	
	 
    @RequestMapping(
    		path = "/clientes",
    		method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ClienteRestDto>> getClientes() {
    	LOG.info("Se llamo al get clientes");
    	return new ResponseEntity<>(clienteService.getClientes(), HttpStatus.OK);

	}
    
  
    @RequestMapping(value = "/{id}", 
    		method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ClienteRestDto> getClientePorId(
    		 @PathVariable("id") Long id) {
    	LOG.info("Se llamo al get clientes x id");
    	return new ResponseEntity<>(clienteService.getClienteById(id), HttpStatus.OK);

	}

    
    @RequestMapping(
    		path = "/save",
    		method = RequestMethod.POST, 
    		produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteRestDto> update(@RequestBody ClienteRestDto clienteRestDto)
    { 
    	LOG.info("Se llamo al update Cliente");
    	
    	return new ResponseEntity<>(clienteService.updateCliente(clienteRestDto), HttpStatus.OK);
                
    }
    
    @RequestMapping(
    		path = "/remove/{id}",
    		method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Boolean> removeCliente(
   		 @PathVariable("id") Long id) {
    	LOG.info("Se llamo al remove Cliente");
    	return new ResponseEntity<>(clienteService.removeClienteById(id), HttpStatus.OK);

	}

    

}
