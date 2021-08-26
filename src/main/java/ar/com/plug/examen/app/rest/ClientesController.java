package ar.com.plug.examen.app.rest;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Clientes;
import ar.com.plug.examen.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	private final ClienteService clienteService;
	
	public ClientesController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	/**
    En este controlador encontramos el ABM de clientes
    */
	
	@PostMapping(path = "/saveCliente", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> saveCliente(@RequestBody Clientes clientes) {
		return new ResponseEntity<ResponseDTO>(clienteService.saveClientes(clientes), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteCliente")
	public ResponseEntity<ResponseDTO> deleteCliente(@Param(value = "id") Integer id) {
		return new ResponseEntity<ResponseDTO>(clienteService.deleteClientes(id), HttpStatus.OK);
	}

	@PutMapping(path = "/updateCliente", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updateCliente(@RequestBody Clientes clientes) {
		return new ResponseEntity<ResponseDTO>(clienteService.updateClientes(clientes), HttpStatus.OK);
	}

}
