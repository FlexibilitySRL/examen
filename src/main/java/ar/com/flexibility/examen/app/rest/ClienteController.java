package ar.com.flexibility.examen.app.rest;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.com.flexibility.examen.domain.entity.Cliente;
import ar.com.flexibility.examen.domain.repositorys.ClienteRepo;




@RestController
@RequestMapping(path ="/clientes")
//este controlador maneja todos los endpoints de los clientes arrojando
//mensajes de ayuda en la consola
public class ClienteController {
	public Logger loggercliente = Logger.getLogger(Cliente.class.getSimpleName());
	@Autowired
	ClienteRepo clienterepo;

	
	//obtener todos los clientes
	@GetMapping("/todos")
	public Iterable<Cliente> Obtenertodos(){
		
		loggercliente.info("obteniendo todos los cliente");
		return clienterepo.findAll();
		
	}
	
	// crear un nuevo cliente
	@PostMapping("/nuevo")
	public Cliente Crearcliente(@Valid @RequestBody Cliente cli){
		Cliente gcliente = clienterepo.save(cli);
		loggercliente.info("cliente guardado correctamente");
		return gcliente;
	}
	
	//buscar un cliente por nombre
	@GetMapping("/buscar/{dni}")
	public Cliente BuscaporID(@PathVariable(value = "dni") String nombre) {
		loggercliente.info("obteniendo cliente con dni seleccionado");
        return clienterepo.findByNombre(nombre);
    }
	// Actualizar un cliente ingresando el id de cliente
    @PutMapping("/actualizar/{id}")
    public Cliente Actcliente(@PathVariable(value = "id") Integer clienteId,
    						  @Valid @RequestBody Cliente clienteDatos)  {
	Cliente cli = clienterepo.findOne(clienteId);
	cli.setNombre(clienteDatos.getNombre());
	cli.setApellido(clienteDatos.getApellido());
	cli.setDni(clienteDatos.getDni());
	cli.setTelefono(clienteDatos.getTelefono());
	
	Cliente actcli = clienterepo.save(cli);
	loggercliente.info("registro guardado correctamente");
	return actcli;
    }
    
    //borrar cliente  atraves de su id
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> Borrarcliente(@PathVariable(value = "id") Integer clienteId) {
       Cliente buscacli =clienterepo.findOne(clienteId);
       clienterepo.delete(buscacli);
       loggercliente.info("registro eliminado corretamente");
       return ResponseEntity.ok().build();
    }
	
	

}
