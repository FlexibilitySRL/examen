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

import ar.com.flexibility.examen.domain.model.Comprobante;
import ar.com.flexibility.examen.domain.model.Vendedor;
import ar.com.flexibility.examen.domain.service.impl.ComprobanteServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.VendedorServiceImpl;
import ar.com.flexibility.examen.utils.Utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@RestController
@ComponentScan(basePackages={"ar.com.flexibility.examen.*"})
@RequestMapping("/vendedores")
public class VendedorController {

	    @Autowired
	    VendedorServiceImpl vendedorService;
	    @Autowired
	    ComprobanteServiceImpl comprobanteService;
	    
	    private Logger logger = LogManager.getLogger(VendedorController.class);
	    
	    // Lista todos los vendedores
	    @GetMapping("/listar")
	    public ArrayList<Vendedor> getAllVendedores() {
	    	ArrayList<Vendedor> list = new ArrayList<>();
			Iterable<Vendedor> agendas = vendedorService.findAll();
			agendas.forEach(list::add);
			logger.info("Listado de Vendedores - EJECUCION EXITOSA");
			return list;
	    }
	    
	    // Crea un nuevo vendedor
	    @PostMapping("/crear")
	    public ResponseEntity<?> createVendedor(@Valid @RequestBody Vendedor vendedor) {
	    Vendedor vendedorRepetido = vendedorService.findByIdVendedor(vendedor.getIdVendedor());
	    	if (vendedor != null) {
	    		if(vendedorRepetido == null) {
	    			vendedorService.save(vendedor);
		    		logger.info("Creacion de Vendedores - EJECUCION EXITOSA");
			    	return new ResponseEntity<>(Utils.customResponse("Vendedor creado correctamente.", true, false),
							HttpStatus.OK);
	    		}else {
	    			logger.info("Creacion de Vendedores - EJECUCION FALLIDA");
		    		return new ResponseEntity<>(Utils.customResponse("El vendedor que desea ingresar ya existe", false, true),
		    				HttpStatus.BAD_REQUEST);
	    		}
	    	}else {
	    		logger.info("Creacion de Vendedores - EJECUCION FALLIDA");
	    		return new ResponseEntity<>(Utils.customResponse("Ingreso de datos incorrecto, por favor verifique de nuevo", false, true),
	    				HttpStatus.BAD_REQUEST);
	    	}
	    }

	    // Modifica el vendedor
	    @PostMapping("/modificar/{idVendedor}")
	    public ResponseEntity<?> updateVendedor(@PathVariable(value = "idVendedor") Integer idVendedor,
	                                            @Valid @RequestBody Vendedor vendedorDetails) {
	    	Vendedor vendedor = vendedorService.findByIdVendedor(idVendedor);
	    	 if (vendedor != null) {
	    		vendedor.setIdVendedor(vendedorDetails.getIdVendedor());
		    	vendedorService.save(vendedor);
	    		logger.info("Modificacion de vendedores - EJECUCION EXITOSA");
		    	return new ResponseEntity<>(Utils.customResponse("Vendedor modificado correctamente.", true, false),
						HttpStatus.OK);
	    	 }else {
	    		logger.info("Modificacion de vendedores - EJECUCION FALLIDA");
	    		return new ResponseEntity<>(Utils.customResponse("El Vendedor que esta intentando modificar no existe en la base de datos.", false, true),
	    				HttpStatus.BAD_REQUEST);
	    	 }
	        
	    }
	    // Borra un vendedor
	    @DeleteMapping("/eliminar/{idVendedor}")
	    public ResponseEntity<?> deleteVendedor(@PathVariable(value = "idVendedor") Integer idVendedor) {
	    	Vendedor vendedor = vendedorService.findByIdVendedor(idVendedor);
	    	 if(comprobanteService != null && vendedor != null) {
	 	    	ArrayList <Comprobante> comprobantes = (ArrayList<Comprobante>) comprobanteService.findAll();
	 		    	for (Comprobante c: comprobantes) {
	 		    		if(c.getVendedor() != null) {
	 		    			if(!(c.getVendedor().getIdVendedor() == vendedor.getIdVendedor())){
	 					        	vendedorService.removeByIdVendedor(idVendedor);
	 								logger.info("Eliminacion de Vendedores - EJECUCION EXITOSA");
	 								return new ResponseEntity<>(Utils.customResponse("Se eliminó exitosamente el vendedor", true, false),
	 										HttpStatus.OK);
	 		    			}else {
	 		    				logger.info("Eliminacion de Vendedores - EJECUCION FALLIDA SE INTENTA ELIMINAR UN VENDEDOR CON COMPROBANTES ASOCIADOS");
	 							return new ResponseEntity<>(
	 									Utils.customResponse("El Vendedor que esta intentando eliminar tiene comprobantes asociados.", false, true), 
	 									HttpStatus.BAD_REQUEST);
	 		    			}
	 			    	}
	 		    	}
	 	    	}else {
	 	    	logger.info("Eliminacion de Vendedores - EJECUCION FALLIDA");
	 	    	return new ResponseEntity<>(Utils.customResponse("El vendedor que desea eliminar no existe en la base de datos", false, true),
	 					HttpStatus.OK);
	 	    	}
	 	    logger.info("Eliminacion de Vendedores - EJECUCION FALLIDA");
	     	return new ResponseEntity<>(Utils.customResponse("El vendedor que desea eliminar no existe en la base de datos", false, true),
	 				HttpStatus.OK);
	 	    }
	    
	  
}
