package ar.com.plug.examen.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.business.domain.Compra;
import ar.com.plug.examen.business.service.FacturaService;
import ar.com.plug.examen.data.entity.Factura;
import ar.com.plug.examen.data.entity.Producto;
import ar.com.plug.examen.data.repository.FacturaRepository;

@RestController
public class CompraController {
	
	public static final Logger logger = Logger.getLogger(CompraController.class);
	
	
	private final FacturaService facturaService;
	private final FacturaRepository facturaRepository;
	
	private CompraController(FacturaService facturaService,FacturaRepository facturaRepository) {
		this.facturaService = facturaService;
		this.facturaRepository = facturaRepository;
	}

	
	@PostMapping(path="/compras", produces = {MediaType.APPLICATION_JSON_VALUE },consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Compra> crearProducto(@RequestBody Compra compra){
		try {		
			Factura _factura = this.facturaService.procesarCompra(compra);
			compra.setIdFactura(_factura.getIdFactura());			
			return new ResponseEntity<>(compra, HttpStatus.CREATED);			
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/compras")
	public  ResponseEntity<Iterable<Factura>> listarCompras(){ 
		try {
			List<Factura>  lista = new ArrayList<Factura>();
			this.facturaRepository.findAll().forEach(lista::add);
			
			if (lista.isEmpty()) {
				logger.trace("La lista de facturas esta vacia.");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.trace("Se retorna(n) "+lista.size()+" registro(s).");
			return new ResponseEntity<>(lista, HttpStatus.OK);			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path="/compras/{id}")
	public  ResponseEntity<Factura> aprobarCompra(@PathVariable("id") long id , @RequestBody  Producto producto){
		try {
			Factura _factura = this.facturaService.aprobarCompra(id);
			logger.trace("Registro para actualizar ID: "+id); 
			return new ResponseEntity<>(_factura, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
