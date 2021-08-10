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

import ar.com.plug.examen.domain.converter.TransaccionToTransaccionResponseDtoConverter;
import ar.com.plug.examen.domain.dto.TransaccionAltaDto;
import ar.com.plug.examen.domain.dto.TransaccionResponseDto;
import ar.com.plug.examen.domain.dto.TransaccionUpdateDto;
import ar.com.plug.examen.domain.exception.ServiceException;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.service.TransaccionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(path = "/transacciones")
@RestController
public class TransaccionController {

	private static final Logger LOGGER = LogManager.getLogger(TransaccionController.class);

	@Autowired
	private TransaccionService transaccionService;

	@Autowired
	private TransaccionToTransaccionResponseDtoConverter converter;

	@ApiOperation(value = "Permite dar de alta una nueva transaccion")
	@ApiResponses({ @ApiResponse(code = 202, message = "Created"),
			@ApiResponse(code = 400, message = "Los datos ingresados no son válidos") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaccion> insert(@NotNull @Valid @RequestBody TransaccionAltaDto request) {
		ResponseEntity<Transaccion> response = ResponseEntity.badRequest().build();
		try {
			Transaccion createdTransaction = transaccionService.createTransaction(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(createdTransaction);
			LOGGER.info("Se creó exitosamente la transaccion con id: " + createdTransaction.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar dar de alta una transaccion", e);
		}
		return response;
	}

	@ApiOperation(value = "Permite actualizar una transaccion")
	@ApiResponses({ @ApiResponse(code = 202, message = "Updated") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransaccionResponseDto> update(@NotNull @Valid @RequestBody TransaccionUpdateDto request) {
		ResponseEntity<TransaccionResponseDto> response = ResponseEntity.noContent().build();
		try {
			Transaccion updatedTransaction = transaccionService.updateTransaction(request);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(updatedTransaction));
			LOGGER.info("Se actualizó exitosamente la transaccion con id: " + updatedTransaction.getId());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar actualizar la transaccion con id: " + request.getId(), e);
		}
		return response;
	}

	@ApiOperation(value = "Permite eliminar un transaccion")
	@ApiResponses({ @ApiResponse(code = 200, message = "Borrado correcto"),
			@ApiResponse(code = 404, message = "El registro que intenta eliminar no existe") })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@NotNull @PathVariable Integer id) {
		ResponseEntity<Void> result = ResponseEntity.ok().build();
		try {
			transaccionService.deleteTransaction(id);
			LOGGER.info("Se eliminó exitosamente la transaccion con id: " + id);
		} catch (Exception e) {
			result = ResponseEntity.notFound().build();
			LOGGER.error("Ocurrió un error al intentar eliminar la transaccion con id: " + id, e);
		}
		return result;
	}

	@ApiOperation(value = "Obtiene todas las transaccions registrados en el sistema")
	@GetMapping()
	public ResponseEntity<Set<TransaccionResponseDto>> findAll() {
		Set<TransaccionResponseDto> result = transaccionService.getTransactions().stream().map(f -> converter.convert(f))
				.collect(Collectors.toSet());
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Obtiene una transaccion por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransaccionResponseDto> getTransaccionById(@NotNull @PathVariable Integer id) {
		ResponseEntity<TransaccionResponseDto> response = ResponseEntity.notFound().build();
		Optional<Transaccion> optionalTransaction = transaccionService.findById(id);
		if (optionalTransaction.isPresent()) {
			response = ResponseEntity.ok(converter.convert(optionalTransaction.get()));
		}
		return response;
	}

	@ApiOperation(value = "Permite aprobar un transaccion")
	@ApiResponses({ @ApiResponse(code = 202, message = "Approbed") })
	@PutMapping(value = "/aprobar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransaccionResponseDto> approbe(@NotNull @PathVariable Integer id) {
		ResponseEntity<TransaccionResponseDto> response = ResponseEntity.noContent().build();
		try {
			Transaccion updatedTransaction = transaccionService.approbe(id);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(updatedTransaction));
			LOGGER.info("Se aprobó exitosamente la transaccion con id: " + updatedTransaction.getId());
		} catch (ServiceException e) {
			LOGGER.error("Ocurrió un error al intentar cancelar la transaccion con id: " + id, e);
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar aprobar la transaccion con id: " + id, e);
		}
		return response;
	}

	@ApiOperation(value = "Permite cancelar un transaccion")
	@ApiResponses({ @ApiResponse(code = 202, message = "Canceled") })
	@PutMapping(value = "/cancelar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransaccionResponseDto> cancel(@NotNull @PathVariable Integer id) {
		ResponseEntity<TransaccionResponseDto> response = ResponseEntity.noContent().build();
		try {
			Transaccion updatedTransaction = transaccionService.cancel(id);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(converter.convert(updatedTransaction));
			LOGGER.info("Se canceló exitosamente la transaccion con id: " + updatedTransaction.getId());
		} catch (ServiceException e) {
			LOGGER.error("Ocurrió un error al intentar cancelar la transaccion con id: " + id, e);
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al intentar cancelar la transaccion con id: " + id, e);
		}
		return response;
	}

}
