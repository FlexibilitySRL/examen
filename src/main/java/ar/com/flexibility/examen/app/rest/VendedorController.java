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
import ar.com.flexibility.examen.domain.entity.Vendedor;
import ar.com.flexibility.examen.domain.repositorys.VendedorRepo;
@RestController
@RequestMapping(path ="/vendedor")
public class VendedorController {
	
	@Autowired
	VendedorRepo vendrepo;
	
	//obtener un nuevo cliente
	@GetMapping("/todos")
	public Iterable<Vendedor> Obtenertodos(){
		return vendrepo.findAll();
	}
	
	// crear un nuevo vendedor
	@PostMapping("/nuevo")
	public Vendedor Crearpro(@Valid @RequestBody Vendedor vend){
		return vendrepo.save(vend);
	}
	
	//buscar un vendedor por cedula
	@GetMapping("/buscar/{dni}")
	public Vendedor BuscaporID(@PathVariable(value = "dni") Integer dnivend) {
        return  vendrepo.findByDni(dnivend);
    }
	// Actualizar un vendedor
    @PutMapping("/actualizar/{id}")
    public Vendedor Actcliente(@PathVariable(value = "id") Integer vendId,
    						  @Valid @RequestBody Vendedor vendDatos)  {
	Vendedor vend =vendrepo.findOne(vendId);
	vend.setNombre(vendDatos.getNombre());
	vend.setApellido(vendDatos.getApellido());
	vend.setDni(vendDatos.getDni());
	Vendedor actpro =vendrepo.save(vend);
	
	return actpro;
    }
    
    //borrar vendedor
    
    @DeleteMapping("/borrar/{dni}")
    public ResponseEntity<?> Borrarcliente(@PathVariable(value = "dni") Integer vendId) {
       Vendedor buscavend =vendrepo.findOne(vendId);
       vendrepo.delete(buscavend);
       return ResponseEntity.ok().build();
    }
}
