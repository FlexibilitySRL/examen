package ar.com.flexibility.examen.app.rest;
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
public class ClienteController {
	
	@Autowired
	ClienteRepo clienterepo;
	
	//obtener todos los clientes
	@GetMapping("/todos")
	public Iterable<Cliente> Obtenertodos(){
		
		
		return clienterepo.findAll();
		
	}
	
	// crear un nuevo cliente
	@PostMapping("/nuevo")
	public Cliente Crearcliente(@Valid @RequestBody Cliente cli){
		return clienterepo.save(cli);
	}
	
	//buscar un cliente por id
	@GetMapping("/buscar/{dni}")
	public Cliente BuscaporID(@PathVariable(value = "dni") Integer clienteDni) {
        return clienterepo.findByDni(clienteDni);
    }
	// Actualizar un cliente
    @PutMapping("/actualizar/{id}")
    public Cliente Actcliente(@PathVariable(value = "id") Integer clienteId,
    						  @Valid @RequestBody Cliente clienteDatos)  {
	Cliente cli = clienterepo.findOne(clienteId);
	cli.setNombre(clienteDatos.getNombre());
	cli.setApellido(clienteDatos.getApellido());
	cli.setDni(clienteDatos.getDni());
	cli.setTelefono(clienteDatos.getTelefono());
	
	Cliente actcli = clienterepo.save(cli);
	
	return actcli;
    }
    
    //borrar cliente
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> Borrarcliente(@PathVariable(value = "id") Integer clienteId) {
       Cliente buscacli =clienterepo.findOne(clienteId);
       clienterepo.delete(buscacli);
       return ResponseEntity.ok().build();
    }
	
	

}
