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

import ar.com.flexibility.examen.domain.entity.Producto;
import ar.com.flexibility.examen.domain.repositorys.ProductRepo;

public class ProductoController {
	@Autowired
	ProductRepo prodrepo;
	
	//obtener un nuevo cliente
	@GetMapping("/todos")
	public Iterable<Producto> Obtenertodos(){
		return prodrepo.findAll();
	}
	
	// crear un nuevo producto
	@PostMapping("/nuevo")
	public Producto Crearpro(@Valid @RequestBody Producto pro){
		return prodrepo.save(pro);
	}
	
	//buscar producto por id
	@GetMapping("/buscar/{id}")
	public Producto BuscaporID(@PathVariable(value = "id") Integer idpro) {
        return  prodrepo.findOne(idpro);
    }
	// Actualizar un vendedor
    @PutMapping("/actualizar/{id}")
    public Producto Actcliente(@PathVariable(value = "id") Integer proId,
    						  @Valid @RequestBody Producto proDatos)  {
	Producto pro =prodrepo.findOne(proId);
	pro.setDescripcion(proDatos.getDescripcion());
	pro.setTipo(proDatos.getTipo());
	pro.setPrecio(proDatos.getPrecio());
	Producto actpro =prodrepo.save(pro);
	
	return actpro;
    }
    
    //borrar producto
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> Borrarcliente(@PathVariable(value = "id") Integer proId) {
       Producto buscapro =prodrepo.findOne(proId);
       prodrepo.delete(buscapro);
       return ResponseEntity.ok().build();
    }

}
