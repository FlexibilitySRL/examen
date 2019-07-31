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
import ar.com.flexibility.examen.domain.entity.Producto;
import ar.com.flexibility.examen.domain.repositorys.ProductRepo;


@RestController
@RequestMapping(path ="/productos")
public class ProductoController {
	// este controlador maneja todos los endpoints de producto arrojando
	// mensajes de ayuda en la consola
	@Autowired
	ProductRepo prodrepo;
	public Logger loggerprod = Logger.getLogger(Cliente.class.getSimpleName());
	
	// en este se obtienen todos los productos
	@GetMapping("/todos")
	public Iterable<Producto> Obtenertodos(){
		loggerprod.info("obteniendo productos");
		return prodrepo.findAll();
	}
	
	// este se encarga de crear un nuevo producto
	@PostMapping("/nuevo")
	public Producto Crearpro(@Valid @RequestBody Producto pro){
		Producto gproduct = prodrepo.save(pro);
		loggerprod.info("producto guardado correctamente");
		return gproduct;
	}
	
	// este  busca un producto por su id 
	@GetMapping("/buscar/{id}")
	public Producto BuscaporID(@PathVariable(value = "id") Integer idpro) {
		loggerprod.info("mostrando resultados de busqueda");
        return  prodrepo.findOne(idpro);
    }
	// este actualiza un producto recibiendo el parametro id
    @PutMapping("/actualizar/{id}")
    public Producto Actcliente(@PathVariable(value = "id") Integer proId,
    						  @Valid @RequestBody Producto proDatos)  {
	Producto pro =prodrepo.findOne(proId);
	pro.setDescripcion(proDatos.getDescripcion());
	pro.setTipo(proDatos.getTipo());
	pro.setPrecio(proDatos.getPrecio());
	Producto actpro =prodrepo.save(pro);
	
	loggerprod.info("registro actualizado correctamente");
	return actpro;
    }
    
    // este se encarga de borrar un producto especifico a traves del id
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> Borrarcliente(@PathVariable(value = "id") Integer proId) {
       Producto buscapro =prodrepo.findOne(proId);
       prodrepo.delete(buscapro);
       loggerprod.info("producto eliminado correctamente");
       return ResponseEntity.ok().build();
    }

}
