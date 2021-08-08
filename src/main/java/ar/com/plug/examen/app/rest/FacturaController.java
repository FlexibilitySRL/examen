package ar.com.plug.examen.app.rest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import ar.com.plug.examen.domain.converter.FacturaToFacturaResponseDtoConverter;
import ar.com.plug.examen.domain.dto.FacturaAltaDto;
import ar.com.plug.examen.domain.dto.FacturaResponseDto;
import ar.com.plug.examen.domain.dto.FacturaUpdateDto;
import ar.com.plug.examen.domain.exception.ServiceException;
import ar.com.plug.examen.domain.model.Factura;
import ar.com.plug.examen.domain.service.FacturaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(path = "/facturas")
@RestController
public class FacturaController {

	private static final Logger LOGGER = LogManager.getLogger(FacturaController.class);

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private FacturaToFacturaResponseDtoConverter converter;

	@ApiOperation(value = "Permite dar de alta una nueva factura")
	@ApiResponses({ @ApiResponse(code = 202, message = "Created"),
			@ApiResponse(code = 400, message = "Los datos ingresados no son válidos") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Factura> insert(@NotNull @Valid @RequestBody FacturaAltaDto request) {
		ResponseEntity<Factura> response = ResponseEntity.badRequest().build();
		try {
			Factura createdInvoice = facturaService.createInvoice(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(createdInvoice);
			LOGGER.info("Se creó exitosamente la factura con id: " + createdInvoice.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar dar de alta una factura", e);
		}
		return response;
	}

	@ApiOperation(value = "Permite actualizar una factura")
	@ApiResponses({ @ApiResponse(code = 202, message = "Updated") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FacturaResponseDto> update(@NotNull @Valid @RequestBody FacturaUpdateDto request) {
		ResponseEntity<FacturaResponseDto> response = ResponseEntity.noContent().build();
		try {
			Factura updatedInvoice = facturaService.updateInvoice(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(updatedInvoice));
			LOGGER.info("Se actualizó exitosamente la factura con id: " + updatedInvoice.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar actualizar la factura con id: " + request.getId(), e);
		}
		return response;
	}

	@ApiOperation(value = "Permite eliminar un factura")
	@ApiResponses({ @ApiResponse(code = 200, message = "Borrado correcto"),
			@ApiResponse(code = 404, message = "El registro que intenta eliminar no existe") })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@NotNull @PathVariable Integer id) {
		ResponseEntity<Void> result = ResponseEntity.ok().build();
		try {
			facturaService.deleteInvoice(id);
			LOGGER.info("Se eliminó exitosamente la factura con id: " + id);
		} catch (Exception e) {
			result = ResponseEntity.notFound().build();
			LOGGER.error("Ocurrió un error al intentar eliminar la factura con id: " + id, e);
		}
		return result;
	}

	@ApiOperation(value = "Obtiene todas las facturas registrados en el sistema")
	@GetMapping()
	public ResponseEntity<Set<FacturaResponseDto>> findAll() {
		Set<FacturaResponseDto> result = facturaService.getInvoices().stream().map(f -> converter.convert(f))
				.collect(Collectors.toSet());
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Obtiene una factura por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FacturaResponseDto> getFacturaById(@NotNull @PathVariable Integer id) {
		ResponseEntity<FacturaResponseDto> response = ResponseEntity.notFound().build();
		Optional<Factura> optionalInvoice = facturaService.findById(id);
		if (optionalInvoice.isPresent()) {
			response = ResponseEntity.ok(converter.convert(optionalInvoice.get()));
		}
		return response;
	}

	@ApiOperation(value = "Permite aprobar un factura")
	@ApiResponses({ @ApiResponse(code = 202, message = "Approbed") })
	@PutMapping(value = "/aprobar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FacturaResponseDto> approbe(@NotNull @PathVariable Integer id) {
		ResponseEntity<FacturaResponseDto> response = ResponseEntity.noContent().build();
		try {
			Factura updatedInvoice = facturaService.approbe(id);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(updatedInvoice));
			LOGGER.info("Se aprobó exitosamente la factura con id: " + updatedInvoice.getId());
		} catch (ServiceException e) {
			LOGGER.error("Ocurrió un error al intentar cancelar la factura con id: " + id, e);
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar aprobar la factura con id: " + id, e);
		}
		return response;
	}

	@ApiOperation(value = "Permite cancelar un factura")
	@ApiResponses({ @ApiResponse(code = 202, message = "Canceled") })
	@PutMapping(value = "/cancelar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FacturaResponseDto> cancel(@NotNull @PathVariable Integer id) {
		ResponseEntity<FacturaResponseDto> response = ResponseEntity.noContent().build();
		try {
			Factura updatedInvoice = facturaService.cancel(id);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(updatedInvoice));
			LOGGER.info("Se canceló exitosamente la factura con id: " + updatedInvoice.getId());
		} catch (ServiceException e) {
			LOGGER.error("Ocurrió un error al intentar cancelar la factura con id: " + id, e);
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar cancelar la factura con id: " + id, e);
		}
		return response;
	}

}
