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
import ar.com.flexibility.examen.domain.service.impl.ClienteServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ComprobanteServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.VendedorServiceImpl;
import ar.com.flexibility.examen.utils.Utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@RestController
@ComponentScan(basePackages={"ar.com.flexibility.examen.*"})
@RequestMapping("/comprobantes")
public class ComprobanteController {

	    @Autowired
	    ComprobanteServiceImpl comprobanteService;
	    ClienteServiceImpl clienteService;
	    VendedorServiceImpl vendedorService;
	    
	    private Logger logger = LogManager.getLogger(VendedorController.class);
	    
	    // Lista todos los comprobantes
	    @GetMapping("/listar")
	    public ArrayList<Comprobante> getAllComprobantes() {
	    	ArrayList<Comprobante> list = new ArrayList<>();
			Iterable<Comprobante> agendas = comprobanteService.findAll();
			agendas.forEach(list::add);
			logger.info("Listado de Comprobantes - EJECUCION EXITOSA");
			return list;
	    }
	    
	    // Crea un nuevo comprobante
	    @PostMapping("/crear")
	    public ResponseEntity<?> createComprobante(@Valid @RequestBody Comprobante comprobante) {
	    Comprobante comprobanteRepetido = comprobanteService.findByIdComprobante(comprobante.getIdComprobante());
	    	if (comprobante != null) {
	    		if(comprobanteRepetido == null) {
	    			comprobante.setAutorizado(false);
		    		comprobante.setEmpleado(null);
		    		comprobante.setFechaAutorizacion(null);
		    		comprobante.setCliente(comprobante.getCliente());
		    		comprobante.setVendedor(comprobante.getVendedor());
		    		comprobante.setRenglon(comprobante.getRenglon());
		    		comprobanteService.save(comprobante);
		    		logger.info("Creacion de Comprobantes - EJECUCION EXITOSA");
			    	return new ResponseEntity<>(Utils.customResponse("Comprobante creado correctamente.", true, false),
							HttpStatus.OK);
	    		}else {
	    			logger.info("Creacion de Comprobantes - EJECUCION FALLIDA");
		    		return new ResponseEntity<>(Utils.customResponse("El comprobante que desea ingresar ya existe", false, true),
		    				HttpStatus.BAD_REQUEST);
	    		}

	    	}else {
	    		logger.info("Creacion de Comprobantes - EJECUCION FALLIDA");
	    		return new ResponseEntity<>(Utils.customResponse("Ingreso de datos incorrecto, por favor verifique de nuevo", false, true),
	    				HttpStatus.BAD_REQUEST);
	    	}
	    }

	    // Modifica el comprobante
	    @PostMapping("/modificar/{idComprobante}")
	    public ResponseEntity<?> updateComprobante(@PathVariable(value = "idComprobante") Integer idComprobante,
	                                            @Valid @RequestBody Comprobante comprobanteDetails) {
	    	Comprobante comprobante = comprobanteService.findByIdComprobante(idComprobante);
	    	 if (comprobante != null) {
	    		comprobante.setCliente(comprobanteDetails.getCliente());
	    		comprobante.setAutorizado(false);
	    		comprobante.setEmpleado(null);
	    		comprobante.setFechaAutorizacion(null);
	    		comprobante.setEmpleado(comprobanteDetails.getEmpleado());
	    		comprobante.setRenglon(comprobanteDetails.getRenglon());
	    		comprobanteService.save(comprobante);
	    		logger.info("Modificacion de comprobantes - EJECUCION EXITOSA");
		    	return new ResponseEntity<>(Utils.customResponse("Comprobante modificado correctamente.", true, false),
						HttpStatus.OK);
	    	 }else {
	    		logger.info("Modificacion de comprobantes - EJECUCION FALLIDA");
	    		return new ResponseEntity<>(Utils.customResponse("El Comprobante que esta intentando modificar no existe en la base de datos.", false, true),
	    				HttpStatus.BAD_REQUEST);
	    	 }
	        
	    }
	    // Borra un comprobante
	    @DeleteMapping("/eliminar/{idComprobante}")
	    public ResponseEntity<?> deleteComprobante(@PathVariable(value = "idComprobante") Integer idComprobante) {
	    	Comprobante comprobante = comprobanteService.findByIdComprobante(idComprobante);
	        if (comprobante != null) {
	        	comprobanteService.removeByIdComprobante(idComprobante);
				logger.info("Eliminacion de Comprobantes - EJECUCION EXITOSA");
				return new ResponseEntity<>(Utils.customResponse("Se eliminó exitosamente el producto", true, false),
						HttpStatus.OK);
			} else {
				logger.info("Eliminacion de Comprobantes - EJECUCION FALLIDA SE INTENTA ELIMINAR UN PRODUCTO INEXISTENTE");
				return new ResponseEntity<>(
						Utils.customResponse("El Comprobante que esta intentando eliminar no existe en la base de datos.", false, true), 
						HttpStatus.BAD_REQUEST);
			}
	    }
	    
	 // Valida un comprobante
	    @PostMapping("/validar/{idComprobante}")
	    public ResponseEntity<?> validarVendedor(@PathVariable(value = "idComprobante") Integer idComprobante,
	                                            @Valid @RequestBody Comprobante comprobanteDetails) {
	    	Comprobante comprobante = comprobanteService.findByIdComprobante(idComprobante);
	    	 if (comprobante != null) {
	    		comprobante.setAutorizado(true);
	    		comprobante.setEmpleado(comprobanteDetails.getEmpleado());
	    		comprobante.setFechaAutorizacion(Long.valueOf(comprobanteDetails.getFechaAutorizacion()));;
	    		comprobanteService.save(comprobante);
	    		logger.info("Validacion de comprobantes - EJECUCION EXITOSA SE VALIDO EL COMPROBANTE");
		    	return new ResponseEntity<>(Utils.customResponse("Comprobante validado correctamente.", true, false),
						HttpStatus.OK);
	    	 }else {
	    		logger.info("Validacion de comprobantes - EJECUCION FALLIDA NO EXISTE EL COMPROBANTE");
	    		return new ResponseEntity<>(Utils.customResponse("El Comprobante que esta intentando validar no existe en la base de datos.", false, true),
	    				HttpStatus.BAD_REQUEST);
	    	 }
	        
	    }
}
