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

import ar.com.plug.examen.domain.converter.DetalleFacturaToDetalleFacturaResponseDtoConverter;
import ar.com.plug.examen.domain.dto.DetalleFacturaAltaDto;
import ar.com.plug.examen.domain.dto.DetalleFacturaResponseDto;
import ar.com.plug.examen.domain.dto.DetalleFacturaUpdateDto;
import ar.com.plug.examen.domain.model.DetalleFactura;
import ar.com.plug.examen.domain.service.DetalleFacturaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(path = "/detalle_factura")
@RestController
public class DetalleFacturaController {
	
	private static final Logger LOGGER = LogManager.getLogger(DetalleFacturaController.class);
	
	@Autowired
	private DetalleFacturaService detalleFacturaService;
	
	@Autowired
	private DetalleFacturaToDetalleFacturaResponseDtoConverter converter;
	
	@ApiOperation(value = "Permite agregar detalle a una factura existente")
	@ApiResponses({ @ApiResponse(code = 202, message = "Detalle Agregado"), @ApiResponse(code = 400, message = "Los datos ingresados no son válidos") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetalleFacturaResponseDto> insert(@NotNull @Valid @RequestBody DetalleFacturaAltaDto request) {
		ResponseEntity<DetalleFacturaResponseDto> response = ResponseEntity.badRequest().build();
		try {
			DetalleFactura createdInvoiceDetail = detalleFacturaService.createInvoiceDetail(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(createdInvoiceDetail));
			LOGGER.info("Se agregó exitosamente el detalle con id: " + createdInvoiceDetail.getId() + " a la factura con id: " + request.getIdFactura());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar agregar un detalle a la factura con id: " + request.getIdFactura(), e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite actualizar un detalle de una factura existente")
	@ApiResponses({ @ApiResponse(code = 202, message = "Updated") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetalleFacturaResponseDto> update(@NotNull @Valid @RequestBody DetalleFacturaUpdateDto request) {
		ResponseEntity<DetalleFacturaResponseDto> response = ResponseEntity.noContent().build();
		try {
			DetalleFactura updatedInvoiceDetail = detalleFacturaService.updateInvoiceDetail(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(updatedInvoiceDetail));
			LOGGER.info("Se actualizó exitosamente el detalle con id: " + updatedInvoiceDetail.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar actualizar el detalle con id: " + request.getId(), e);
		}
		return response;
	}
	
	@ApiOperation(value = "Permite eliminar un detalle de una factura existente")
	@ApiResponses({ @ApiResponse(code = 200, message = "Borrado correcto"),  @ApiResponse(code = 404, message = "El registro que intenta eliminar no existe")})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@NotNull @PathVariable Integer id) {
		ResponseEntity<Void> result = ResponseEntity.ok().build();
		try {
			detalleFacturaService.deleteInvoiceDetail(id);
			LOGGER.info("Se eliminó exitosamente el detalle con id: " + id);
		} catch (Exception e) {
			result = ResponseEntity.notFound().build();
			LOGGER.error("Ocurrió un error al intentar eliminar el detalle con id: " + id, e);
		}
		return result;
	}
	
}
