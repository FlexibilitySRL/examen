package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.dto.VendedorDTO;
import ar.com.plug.examen.domain.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.service.VendedorService;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

	private VendedorService vendedorService;

	@Autowired
	public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

	@GetMapping
	public  ResponseEntity<List<VendedorDTO>> listarVendedors()  { 
		
		return new ResponseEntity<>(this.vendedorService.listarVendedores(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VendedorDTO> getClientById(@PathVariable long id)  {
		
		return new ResponseEntity<VendedorDTO>(this.vendedorService.consultarVendedorPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public  ResponseEntity<VendedorDTO> crearVendedor(@RequestBody VendedorDTO vendedorDTO)  {		
		
		return new ResponseEntity<VendedorDTO>( this.vendedorService.crearVendedor(vendedorDTO), HttpStatus.CREATED);
	}

	@PutMapping(path="/{id}")
	public  ResponseEntity<VendedorDTO> actualizarVendedor(@PathVariable("id") long id, @RequestBody VendedorDTO vendedorDTO) throws ResourceNotFoundException{
	
		return new ResponseEntity<VendedorDTO>( this.vendedorService.actualizarVendedor(vendedorDTO),HttpStatus.OK);
	}

	@DeleteMapping(path="/{id}")
	public  ResponseEntity<Object> eliminarVendedor(@PathVariable("id") long id) {		
		this.vendedorService.eliminarVendedor(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
