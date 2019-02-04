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
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Comprobante;
import ar.com.flexibility.examen.domain.service.impl.ClienteServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ComprobanteServiceImpl;
import ar.com.flexibility.examen.utils.Utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@RestController
@ComponentScan(basePackages={"ar.com.flexibility.examen.*"})
@RequestMapping("/clientes")
public class ClienteController {

	    @Autowired
	    ClienteServiceImpl clienteService;
	    
	    @Autowired
	    ComprobanteServiceImpl comprobanteService;
	    
	    private Logger logger = LogManager.getLogger(ClienteController.class);
	    
	    // Lista todos los clientes
	    @GetMapping("/listar")
	    public ArrayList<Cliente> getAllCliente() {
	    	ArrayList<Cliente> list = new ArrayList<>();
			Iterable<Cliente> agendas = clienteService.findAll();
			for(Cliente c: agendas) {
				list.add(c);
			}
			logger.info("Listado de Clientes - EJECUCION EXITOSA");
			return list;
	    }
	    
	    // Crea un nuevo cliente
	    @PostMapping("/crear")
	    public ResponseEntity<?> createCliente(@Valid @RequestBody Cliente cliente) {
	    Cliente clienteRepetido = clienteService.findById(cliente.getDni());
    	if (cliente != null) {
    		if(clienteRepetido == null) {
    			clienteService.save(cliente);
        		logger.info("Creacion de Clientes - EJECUCION EXITOSA");
    	    	return new ResponseEntity<>(Utils.customResponse("Cliente creado correctamente.", true, false),
    					HttpStatus.OK);
    		}else {
    			logger.info("Creacion de Clientes - EJECUCION FALLIDA");
        		return new ResponseEntity<>(Utils.customResponse("El cliente que desea ingresar ya existe", false, true),
        				HttpStatus.BAD_REQUEST);
    		}
    		
    	}else {
    		logger.info("Creacion de Clientes - EJECUCION FALLIDA");
    		return new ResponseEntity<>(Utils.customResponse("Ingreso de datos incorrecto, por favor verifique de nuevo", false, true),
    				HttpStatus.BAD_REQUEST);
    		}
	    }

	    // Modifica el cliente
	    @PostMapping("/modificar/{idCliente}")
	    public ResponseEntity<?> updateCliente(@PathVariable(value = "idCliente") Integer idCliente,
	                                            @Valid @RequestBody Cliente clienteDetails) {
	    	Cliente cliente = clienteService.findById(idCliente);
	    	 if (cliente != null) {
		    	cliente.setNombreYApellido(clienteDetails.getNombreYApellido());
		    	cliente.setRazonSocial(clienteDetails.getRazonSocial());
		    	clienteService.save(cliente);
	    		logger.info("Modificacion de Clientes - EJECUCION EXITOSA");
		    	return new ResponseEntity<>(Utils.customResponse("Cliente modificado correctamente.", true, false),
						HttpStatus.OK);
	    	 }else {
	    		logger.info("Modificacion de Clientes - EJECUCION FALLIDA");
	    		return new ResponseEntity<>(Utils.customResponse("El cliente que esta intentando modificar no existe en la base de datos", false, true),
	    				HttpStatus.BAD_REQUEST);
	    	 }
	        
	    }
	    // Borra un cliente
	    @DeleteMapping("/eliminar/{idCliente}")
	    public ResponseEntity<?> deleteCliente(@PathVariable(value = "idCliente") Integer idCliente) {
	    Cliente cliente = clienteService.findById(idCliente);
	    if(comprobanteService != null && cliente != null) {
	    	ArrayList <Comprobante> comprobantes = (ArrayList<Comprobante>) comprobanteService.findAll();
		    	for (Comprobante c: comprobantes) {
		    		if(c.getCliente() != null) {
		    			if(!(c.getCliente().getDni() == cliente.getDni())){
				        	clienteService.removeByid(idCliente);
							logger.info("Eliminacion de Clientes - EJECUCION EXITOSA");
							return new ResponseEntity<>(Utils.customResponse("Se eliminó exitosamente el cliente", true, false),
									HttpStatus.OK);
		    			}else {
		    				logger.info("Eliminacion de Clientes - EJECUCION FALLIDA SE INTENTA ELIMINAR UN CLIENTE CON COMPROBANTES ASOCIADOS");
							return new ResponseEntity<>(
									Utils.customResponse("El cliente que esta intentando eliminar tiene comprobantes asociados.", false, true), 
									HttpStatus.BAD_REQUEST);
		    			}
			    	}
		    	}
	    	}else {
	    	logger.info("Eliminacion de Clientes - EJECUCION FALLIDA");
	    	return new ResponseEntity<>(Utils.customResponse("El cliente que desea eliminar no existe en la base de datos", false, true),
					HttpStatus.OK);
	    	}
	    logger.info("Eliminacion de Clientes - EJECUCION FALLIDA");
    	return new ResponseEntity<>(Utils.customResponse("El cliente que desea eliminar no existe en la base de datos", false, true),
				HttpStatus.OK);
	    }
}
