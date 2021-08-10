package ar.com.plug.examen.app.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.converter.DetalleTransaccionToDetalleTransaccionResponseDtoConverter;
import ar.com.plug.examen.domain.dto.DetalleTransaccionAltaDto;
import ar.com.plug.examen.domain.dto.DetalleTransaccionResponseDto;
import ar.com.plug.examen.domain.dto.DetalleTransaccionUpdateDto;
import ar.com.plug.examen.domain.model.DetalleTransaccion;
import ar.com.plug.examen.domain.service.DetalleTransaccionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(path = "/detalle_transaccion")
@RestController
public class DetalleTransaccionController {
	
	private static final Logger LOGGER = LogManager.getLogger(DetalleTransaccionController.class);
	
	@Autowired
	private DetalleTransaccionService detalleTransaccionService;
	
	@Autowired
	private DetalleTransaccionToDetalleTransaccionResponseDtoConverter converter;
	
	@ApiOperation(value = "Permite agregar detalle a una transaccion existente")
	@ApiResponses({ @ApiResponse(code = 202, message = "Detalle Agregado"), @ApiResponse(code = 400, message = "Los datos ingresados no son válidos") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetalleTransaccionResponseDto> insert(@NotNull @Valid @RequestBody DetalleTransaccionAltaDto request) {
		ResponseEntity<DetalleTransaccionResponseDto> response = ResponseEntity.badRequest().build();
		try {
			DetalleTransaccion createdTransactionDetail = detalleTransaccionService.createTransactionDetail(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(createdTransactionDetail));
			LOGGER.info("Se agregó exitosamente el detalle con id: " + createdTransactionDetail.getId() + " a la transaccion con id: " + request.getIdTransaccion());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar agregar un detalle a la transaccion con id: " + request.getIdTransaccion(), e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite actualizar un detalle de una transaccion existente")
	@ApiResponses({ @ApiResponse(code = 202, message = "Updated") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetalleTransaccionResponseDto> update(@NotNull @Valid @RequestBody DetalleTransaccionUpdateDto request) {
		ResponseEntity<DetalleTransaccionResponseDto> response = ResponseEntity.noContent().build();
		try {
			DetalleTransaccion updatedTransactionDetail = detalleTransaccionService.updateTransactionDetail(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(updatedTransactionDetail));
			LOGGER.info("Se actualizó exitosamente el detalle con id: " + updatedTransactionDetail.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar actualizar el detalle con id: " + request.getId(), e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite eliminar un detalle de una transaccion existente")
	@ApiResponses({ @ApiResponse(code = 200, message = "Borrado correcto"),  @ApiResponse(code = 404, message = "El registro que intenta eliminar no existe")})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@NotNull @PathVariable Integer id) {
		ResponseEntity<Void> result = ResponseEntity.ok().build();
		try {
			detalleTransaccionService.deleteTransactionDetail(id);
			LOGGER.info("Se eliminó exitosamente el detalle con id: " + id);
		} catch (Exception e) {
			result = ResponseEntity.notFound().build();
			LOGGER.error("Ocurrió un error al intentar eliminar el detalle con id: " + id, e);
		}
		return result;
	}
	
}
