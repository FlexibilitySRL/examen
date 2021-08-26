package ar.com.plug.examen.app.rest;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Compras;
import ar.com.plug.examen.domain.service.ComprasService;

@RestController
@RequestMapping("/compra")
public class ComprasController {
	
	private final ComprasService comprasService;
	
	public ComprasController(ComprasService comprasService) {
		this.comprasService = comprasService;
	}
	
	/**
    En este controlador encontramos el Alta y baja de compras
   */
	
	@PostMapping(path = "/saveCompra", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> saveCompra(@RequestBody Compras compras) {
		return new ResponseEntity<ResponseDTO>(comprasService.saveCompra(compras), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteCompra")
	public ResponseEntity<ResponseDTO> deleteCompra(@Param(value = "id") Integer id) {
		return new ResponseEntity<ResponseDTO>(comprasService.deleteCompra(id), HttpStatus.OK);
	}
	
	/**
    con el metodo getCompras obtenemos todas las transacciones de compras que ha hecho un cliente por su id
    */
	
	@GetMapping(path = "/getCompras")
	public ResponseEntity<ResponseDTO> getCompras(@Param(value = "id") Integer id) {
		return new ResponseEntity<ResponseDTO>(comprasService.findByIdcliente(id), HttpStatus.OK);
	}

}
