package ar.com.flexibility.examen.app.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.entities.Cliente;
import ar.com.flexibility.examen.domain.service.ClienteS;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {
	Logger LOG = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("ClienteService")
	ClienteS cs;

	@PutMapping("/agregar")
	public boolean agregarCliente(@RequestBody @Valid Cliente cliente) {
		try {
			LOG.info("Servicio OK");
			return cs.agregar(cliente);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}
	}

	@PostMapping("/modificar")
	public boolean modificarCliente(@RequestBody @Valid Cliente cliente) {
		try {
			LOG.info("Servicio OK");
			return cs.modificar(cliente);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}

	}

	@DeleteMapping("/borrar/{id}")
	public boolean borrarCliente(@PathVariable("id") int id) {
		try {
			LOG.info("Servicio OK");
			return cs.borrar(id);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}

	}

	@GetMapping("/listar")
	public List<Cliente> listarCliente() {
		try {
			LOG.info("Servicio OK");
			return cs.getAll();
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
	}

	@GetMapping("/listar/n/{nombre}")
	public List<Cliente> listarNombreCliente(@PathVariable("nombre") String nombre) {
		try {
			LOG.info("Servicio OK");
			return cs.buscarNombre(nombre);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
	}

	@GetMapping("/listar/a/{apellido}")
	public List<Cliente> listarApellidoCliente(@PathVariable("apellido") String apellido) {
		try {
			LOG.info("Servicio OK");
			return cs.buscarApellido(apellido);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
	}

	@GetMapping("listar/{nombre}/{apellido}")
	public Cliente buscarNombreApellidoCliente(@PathVariable("nombre") String nombre,
			@PathVariable("apellido") String apellido) {
		try {
			LOG.info("Servicio OK");
			return cs.buscarNombreApellido(nombre, apellido);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
	}

	@GetMapping("/listar/{id}")
	public Cliente buscarIdCliente(@PathVariable("id") int id) {
		try {
			LOG.info("Servicio OK");
			return cs.buscarID(id);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}	
	}
}
