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

import ar.com.flexibility.examen.domain.entities.Producto;
import ar.com.flexibility.examen.domain.service.ProductoS;


@RestController
@RequestMapping("/producto")
public class ProductoRest {
	Logger LOG = (Logger) LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    @Qualifier("ProductoService")
    ProductoS ps;
    
    @PutMapping("/agregar")
    public boolean agregarProducto(@RequestBody @Valid Producto producto){
		try {
			LOG.info("Servicio OK");
			return ps.agregar(producto);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}
        
    }


    @PostMapping("/modificar")
    public boolean modificarProducto(@RequestBody @Valid Producto producto){
		try {
			LOG.info("Servicio OK");
			return ps.agregar(producto);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}
        
    }

    @DeleteMapping("/borrar/{id}")
    public boolean borrarProducto(@PathVariable("id") int id){
		try {
			LOG.info("Servicio OK");
			return ps.borrar(id);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}
        
    }
    
    @GetMapping("/listar")
    public List<Producto> listarProducto(){
		try {
			LOG.info("Servicio OK");
			return ps.getAll();
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
        
    }
    
    @GetMapping("/listar/c/{categoria}")
    public List<Producto> listarCategoriaProducto(@PathVariable("categoria") String categoria){
		try {
			LOG.info("Servicio OK");
			return ps.buscarCategoria(categoria);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
        
    }

    @GetMapping("/listar/n/{nombre}")
    public Producto buscarNombreProducto(@PathVariable("nombre") String nombre){
		try {
			LOG.info("Servicio OK");
			return ps.buscarNombre(nombre);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
        
    }

    @GetMapping("/listar/id/{id}")
    public Producto buscarIdProducto(@PathVariable("id") int id){
		try {
			LOG.info("Servicio OK");
			return ps.buscarID(id);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
        
    }
}
