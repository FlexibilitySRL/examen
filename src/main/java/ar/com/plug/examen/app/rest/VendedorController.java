package ar.com.plug.examen.app.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.data.entity.Vendedor;
import ar.com.plug.examen.data.repository.VendedorRepository;

@RestController
public class VendedorController {
	public static final Logger logger = Logger.getLogger(VendedorController.class);

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	public VendedorController(VendedorRepository vendedorRepository) {
		this.vendedorRepository = vendedorRepository;
	}

	@GetMapping("/vendedores")
	public  ResponseEntity<Iterable<Vendedor>> listarVendedors(){ 
		try {
			List<Vendedor>  lista = new ArrayList<Vendedor>();
			this.vendedorRepository.findAll().forEach(lista::add);

			if (lista.isEmpty()) {
				logger.trace("La lista de vendedores esta vacia.");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.trace("Se retorna(n) "+lista.size()+" registro(s).");
			return new ResponseEntity<>(lista, HttpStatus.OK);			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path="/vendedores", produces = {MediaType.APPLICATION_JSON_VALUE },consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Vendedor> crearVendedor(@RequestBody Vendedor cliente){
		try {
			Vendedor _vendedor = this.vendedorRepository.save(cliente);
			logger.trace("Registro Creado ID: "+cliente.getIdVendedor()); 
			logger.trace(cliente.toString());
			return new ResponseEntity<>(_vendedor, HttpStatus.CREATED);			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path="/vendedores/{id}")
	public  ResponseEntity<Vendedor> actualizarVendedor(@PathVariable("id") long id , @RequestBody  Vendedor cliente){
		try {
			Optional<Vendedor> clienteInfo = this.vendedorRepository.findById(id);
			logger.trace("Registro para actualizar ID: "+id); 
			if (clienteInfo.isPresent()) {
				logger.trace("Registro Existe");
				logger.trace("Registro Actualizado"+cliente.toString());
				Vendedor _vendedor = clienteInfo.get();
				_vendedor.setNombre(cliente.getNombre());
				_vendedor.setApellido(cliente.getApellido());
				_vendedor.setEmail(cliente.getEmail());
				_vendedor.setTelefono(cliente.getTelefono());
				return new ResponseEntity<>(this.vendedorRepository.save(_vendedor), HttpStatus.OK);
			}else {
				logger.trace("Registro No Existe"); 
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path="/vendedores/{id}")
	public  ResponseEntity<Vendedor> eliminarVendedor(@PathVariable("id") long id ){
		try {
			logger.trace("Registro para Eliminar ID: "+id); 
			this.vendedorRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		} catch (Exception e) {
			logger.trace("Registro No Existe"); 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

}
