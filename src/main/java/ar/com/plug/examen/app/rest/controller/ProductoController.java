package ar.com.plug.examen.app.rest.controller;

import ar.com.plug.examen.app.rest.controller.docs.ProductoControllerDoc;
import ar.com.plug.examen.domain.model.dto.ProductoRestDto;
import ar.com.plug.examen.domain.service.IProductoService;

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
@RequestMapping(path = "/producto")
public class ProductoController implements ProductoControllerDoc  {

	private static final Logger LOG = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private IProductoService productoService;
	
	 
    @RequestMapping(
    		path = "/productos",
    		method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ProductoRestDto>> getProductos() {
    	
    	LOG.info("Se llamo al get productos");

    	return new ResponseEntity<>(productoService.getProductos(), HttpStatus.OK);

	}
    
  
    @RequestMapping(value = "/{id}", 
    		method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ProductoRestDto> getProductoPorId(
    		 @PathVariable("id") Long id) {
    	LOG.info("Se llamo al get productos x id");
    	return new ResponseEntity<>(productoService.getProductoById(id), HttpStatus.OK);

	}

    
    @RequestMapping(
    		path = "/save",
    		method = RequestMethod.POST, 
    		produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoRestDto> update(@RequestBody ProductoRestDto productoRestDto)
    { 
    	LOG.info("Se llamo al update productos");
    	return new ResponseEntity<>(productoService.updateProducto(productoRestDto), HttpStatus.OK);
                
    }
    
    @RequestMapping(
    		path = "/remove/{id}",
    		method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Boolean> removeProducto(
   		 @PathVariable("id") Long id) {
    	LOG.info("Se llamo al remove productos");

    	return new ResponseEntity<>(productoService.removeProductoById(id), HttpStatus.OK);

	}

    

}
