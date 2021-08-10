package ar.com.plug.examen.app.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.dto.VendedorAltaDto;
import ar.com.plug.examen.domain.dto.VendedorUpdateDto;
import ar.com.plug.examen.domain.model.Vendedor;
import ar.com.plug.examen.domain.service.VendedorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(path = "/vendedores")
@RestController
public class VendedorController {
	
	private static final Logger LOGGER = LogManager.getLogger(VendedorController.class);
	
	@Autowired
	private VendedorService vendedorService;
	
	@ApiOperation(value = "Permite dar de alta un nuevo vendedor")
	@ApiResponses({ @ApiResponse(code = 202, message = "Created"), @ApiResponse(code = 400, message = "Los datos ingresados no son válidos") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vendedor> insert(@NotNull @Valid @RequestBody VendedorAltaDto request) {
		ResponseEntity<Vendedor> response = ResponseEntity.badRequest().build();
		try {
			Vendedor createdSeller = vendedorService.createSeller(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(createdSeller);
			LOGGER.info("Se creó exitosamente el vendedor con id: " + createdSeller.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar dar de alta un vendedor", e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite actualizar un vendedor")
	@ApiResponses({ @ApiResponse(code = 202, message = "Updated") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vendedor> update(@NotNull @Valid @RequestBody VendedorUpdateDto request) {
		ResponseEntity<Vendedor> response = ResponseEntity.noContent().build();
		try {
			Vendedor updatedSeller = vendedorService.updateSeller(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedSeller);
			LOGGER.info("Se actualizó exitosamente el vendedor con id: " + updatedSeller.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar actualizar el vendedor con id: " + request.getId(), e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite eliminar un vendedor")
	@ApiResponses({ @ApiResponse(code = 200, message = "Borrado correcto"),  @ApiResponse(code = 404, message = "El registro que intenta eliminar no existe")})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@NotNull @PathVariable Integer id) {
		ResponseEntity<Void> result = ResponseEntity.ok().build();
		try {
			vendedorService.deleteSeller(id);
			LOGGER.info("Se eliminó exitosamente el vendedor con id: " + id);
		} catch (Exception e) {
			result = ResponseEntity.notFound().build();
			LOGGER.error("Ocurrió un error al intentar eliminar el vendedor con id: " + id, e);
		}
		return result;
	}
	
	@ApiOperation(value = "Obtiene todos lo vendedors registrados en el sistema")
	@GetMapping()
	public ResponseEntity<List<Vendedor>> findAll(){
		return ResponseEntity.ok(vendedorService.getSellers());
	}
	
	@ApiOperation(value = "Obtiene un vendedor por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vendedor> getMandanteById(@PathVariable Integer id) {
		ResponseEntity<Vendedor> response = ResponseEntity.notFound().build();
		Optional<Vendedor> optionalSeller = vendedorService.findById(id);
		if(optionalSeller.isPresent()) {
			response = ResponseEntity.ok(optionalSeller.get());
		}
		return response;
	}
	

}
