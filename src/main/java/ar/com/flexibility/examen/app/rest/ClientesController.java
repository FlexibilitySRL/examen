package ar.com.flexibility.examen.app.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.service.ClienteService;
import ar.com.flexibility.examen.domain.model.Cliente;

@RestController
public class ClientesController {

	@Autowired
	private ClienteService clienteService;
	private static final Log log = LogFactory.getLog(ClientesController.class);
	
	/*
	 * obtiene el cliente que tiene id {id}
	 */
	@RequestMapping("/clientes/{id}")
	public ResponseEntity<?> getCliente(@PathVariable("id") long id) {
		
		log.info("Get cliente " + id);
		
		Cliente cliente = clienteService.findById(id);
		if (cliente == null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	/*
	 * obtiene las compras hechas por el cliente {id}
	 */
	/*
	@RequestMapping("/clientes/{id}/compras")
	public ResponseEntity<?> getCompras(@PathVariable("id") long id) {
		
		log.info("Get compras del cliente " + id);
		
		Cliente cliente = clienteService.findById(id);
		if (cliente == null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.NOT_FOUND);
		}
		
	//	List<Compra> compras = comprasService.findByClienteId(id);
		
	//	return new ResponseEntity<Cliente>(compras, HttpStatus.OK);
	}*/
	
	// TODO resto de los endpoints 
	// GET /clientes/ obtener todos los cliente
	// PUT /clientes/{id} atualizar cliente
	// DEL /clientes/{id} borrar cliente
	// POST /clientes/ crear nuevo cliente

	
	
}
