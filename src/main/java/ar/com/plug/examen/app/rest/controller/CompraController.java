package ar.com.plug.examen.app.rest.controller;

import ar.com.plug.examen.app.rest.controller.docs.CompraControllerDoc;
import ar.com.plug.examen.domain.model.dto.ClienteCompraRestDto;
import ar.com.plug.examen.domain.model.dto.CompraRestDto;
import ar.com.plug.examen.domain.service.IClienteService;
import ar.com.plug.examen.domain.service.ICompraService;

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
@RequestMapping(path = "/compra")
public class CompraController implements CompraControllerDoc  {

	private static final Logger LOG = LoggerFactory.getLogger(CompraController.class);
	
	@Autowired
	private ICompraService compraService;
	
	@Autowired
	private IClienteService clienteService;
	

	 
    @RequestMapping(
    		path = "/compras",
    		method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CompraRestDto>> getCompras() {
    	LOG.info("Se llamo al get compras");

    	return new ResponseEntity<>(compraService.getCompras(), HttpStatus.OK);

	}
    
  
    @RequestMapping(value = "/{id}", 
    		method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CompraRestDto> getCompraPorId(
    		 @PathVariable("id") Long id) {
    	LOG.info("Se llamo al get compras x id");

    	return new ResponseEntity<>(compraService.getCompraById(id), HttpStatus.OK);

	}

    
    @RequestMapping(
    		path = "/save",
    		method = RequestMethod.POST, 
    		produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompraRestDto> update(
    		@RequestBody final CompraRestDto compraRestDto
    		)
    { 
    	LOG.info("Se llamo al update de compras");

    	return new ResponseEntity<>(compraService.updateCompra(compraRestDto), HttpStatus.OK);
                
    }
    
    @RequestMapping(
    		path = "/remove/{id}",
    		method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Boolean> removeCompra(
   		 @PathVariable("id") Long id) {
    	LOG.info("Se llamo al remove compras");

    	return new ResponseEntity<>(compraService.removeCompraById(id), HttpStatus.OK);

	}


    @RequestMapping(value = "/comprascliente/{id}", 
    		method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<ClienteCompraRestDto> getComprasByCliente(
   		 @PathVariable("id") Long id) {
    	
    	LOG.info("Se llamo al get compras por cliente");

    	return new ResponseEntity<>(clienteService.getClienteComprasById(id), HttpStatus.OK);
	}


    @RequestMapping(
    		path = "/aprobar/{id}",
    		method = RequestMethod.POST)
	public ResponseEntity<Boolean> aprobarCompra(
	   		 @PathVariable("id") Long id) {
    	
    	LOG.info("Se llamo al aprobar compra");

    	return new ResponseEntity<>(compraService.aprobarCompraById(id), HttpStatus.OK);
	}

    

}
