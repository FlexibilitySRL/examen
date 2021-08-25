package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.dto.FacturaDTO;
import ar.com.plug.examen.domain.service.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

	private final FacturaService facturaService;

	private FacturaController(FacturaService facturaService) {
		this.facturaService = facturaService;
	}

	@PostMapping
	public  ResponseEntity<FacturaDTO> crearFactura(@RequestBody FacturaDTO facturaDTO){
		return new ResponseEntity<FacturaDTO>( this.facturaService.crearFactura(facturaDTO), HttpStatus.CREATED);
	}

	@PutMapping(path="/{id}")
	public  ResponseEntity<FacturaDTO> aprobarFactura(@PathVariable("id") long id){

		return new ResponseEntity<>(this.facturaService.aprobarFactura(id), HttpStatus.OK);
	}

	@GetMapping
	public  ResponseEntity<List<FacturaDTO>> listarFacturas()  { 
		
		return new ResponseEntity<>(this.facturaService.listarFacturas(), HttpStatus.OK);
	}


}
