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

import ar.com.flexibility.examen.domain.entities.Compra;
import ar.com.flexibility.examen.domain.repository.ClienteR;
import ar.com.flexibility.examen.domain.repository.ProductoR;
import ar.com.flexibility.examen.domain.service.CompraS;

@RestController
@RequestMapping("/compra")
public class CompraRest {
	Logger LOG = (Logger) LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    @Qualifier("CompraService")
    CompraS cs;
    
    @Autowired
    @Qualifier("clienteRep")
    ClienteR cr;

    @Autowired
    @Qualifier("productoRep")
    ProductoR pr;
    
    @PutMapping("/agregar/{cliente}/{producto}")
    public boolean agregarCompra(
    		@PathVariable("cliente") int clienteID,
    		@PathVariable("producto") int productoID,
    		@RequestBody @Valid Compra compra){
		try {
			LOG.info("Servicio OK");
    	compra.setCliente(cr.findById(clienteID));
    	compra.setProducto(pr.findById(productoID));
    	return cs.agregar(compra);			
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}
    }
    
    @PostMapping("/modificar")
    public boolean modificarCompra(@RequestBody @Valid Compra compra){
		try {
			LOG.info("Servicio OK");
			return cs.agregar(compra);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}
    }
    
    @DeleteMapping("/borrar/{id}")
    public boolean borrarCompra(@PathVariable("id") int id){
		try {
			LOG.info("Servicio OK");
			return cs.borrar(id);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return false;
		}
    }

    @GetMapping("/listar")
    public List<Compra> listarCompras(){
		try {
			LOG.info("Servicio OK");
			return cs.getAll();
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
    }

    @GetMapping("/listar/{estado}")
    public List<Compra>listarEstadoCompra(@PathVariable("estado") boolean estado){
		try {
			LOG.info("Servicio OK");
			return cs.buscarAprobado(estado);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
    }

    @GetMapping("/buscar/{id}")
    public Compra buscarCompraId(@PathVariable("id") int id) {
		try {
			LOG.info("Servicio OK");
			return cs.buscarID(id);
		} catch (Exception e) {
			LOG.error("Error: " + e);
			return null;
		}
    }
}
