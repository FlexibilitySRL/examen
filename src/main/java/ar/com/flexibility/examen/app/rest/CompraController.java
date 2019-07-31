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
import ar.com.flexibility.examen.domain.entity.Compra;
import ar.com.flexibility.examen.domain.repositorys.CompraRepo;
import java.util.logging.*;
@RestController
@RequestMapping(path = "/compra")
public class CompraController {
	@Autowired
	CompraRepo comprarepo;
	
	public Logger loggercompra = Logger.getLogger(CompraController.class.getSimpleName());
	// obtener todas las compras
	@GetMapping("/todos")
	public Iterable<Compra> obtenercompras() {
		loggercompra.info("obteniendo registros");
		return comprarepo.findAll();
	}

	// crear una nueva compra
	@PostMapping("/nueva")
	public Compra CrearCompra(@Valid @RequestBody Compra cmp) {
		loggercompra.info("creando nuevo registro");
		return comprarepo.save(cmp);
		
	}

	// buscar una compra por id
	@GetMapping("/buscar/{id}")
	public Compra BuscaporID(@PathVariable(value = "id") Integer idcompra) {
		Compra busca = comprarepo.findOne(idcompra);
		loggercompra.info("resultados encontrados");
		return busca;
	}
	
	// Actualizar un cliente
    @PutMapping("/actualizar/{id}")
    public Compra Actcompra(@PathVariable(value = "id") Integer compraId,
    						  @Valid @RequestBody Compra compraDatos)  {
	Compra cmp = comprarepo.findOne(compraId);
	cmp.setCant_art(compraDatos.getCant_art());
	cmp.setTipo_pago(compraDatos.getTipo_pago());;
	cmp.setId_cliente(compraDatos.getId_cliente());
	cmp.setId_articulo(compraDatos.getId_articulo());
	cmp.setId_vendedor(compraDatos.getId_vendedor());
	
	Compra actcmp = comprarepo.save(cmp);
	loggercompra.info("registro actualizado");
	return actcmp;
    }
    
    //borrar cliente
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> BorrarCompra(@PathVariable(value = "id") Integer compraid) {
      Compra compraborra =comprarepo.findOne(compraid);
      comprarepo.delete(compraborra);
      loggercompra.info("registro borrado correctamente");
       return ResponseEntity.ok().build();
    }
	
}
