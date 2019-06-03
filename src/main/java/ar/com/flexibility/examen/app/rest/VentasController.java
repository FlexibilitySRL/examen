package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.com.flexibility.examen.domain.model.Ventas;
import ar.com.flexibility.examen.repo.IVentasRepo;

@RestController
@RequestMapping("/ventas")
public class VentasController {
	private static final Log logger = LogFactory.getLog(VentasController.class);
	@Autowired
	private IVentasRepo repo;
	
	@GetMapping
	public List<Ventas> listar(){
		try {
			logger.info("Mostrando lista de ventas");
			return repo.findAll();
		}catch(Exception e) {
			logger.info("Error al desaprobar la venta");
			return null;
		}
	}
	@GetMapping(value = "/{id}")
		public Ventas Buscarventa(@PathVariable("id") Integer id) {
		logger.info("Buscando Venta");
		try {
			logger.info("Venta encontrada");
			return repo.findOne(id);			
		}catch(Exception e) {
			logger.info("Error al encontrar la venta");
			return null;
		}
		
	}
	@PostMapping
	public void alta(@RequestBody Ventas venta) {
		repo.save(venta);
	}
}
