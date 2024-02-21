package ar.com.plug.examen.infrastructure.rest.resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.service.TransactionsService;
import ar.com.plug.examen.infrastructure.rest.dto.ResponseDto;
import ar.com.plug.examen.infrastructure.rest.dto.TransactionRequestDto;
import ar.com.plug.examen.infrastructure.rest.dto.TransactionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Transacction", description = "Manejo de transacciones de compra")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = TransactionsController.PATH)
public class TransactionsController {
	private final TransactionsService transactionsService;
	public final static String PATH = "/transacction";
	public final static String GET_BY_CLIENT_EMAIL = "/client-email";
	public final static String GET_ALL = "/all";
	public final static String CREATE = "/create";
	public final static String APPROVED = "/approved";

	@Operation(summary = "Crea una transaccion por medio de un json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Crea una transaccion y la devuelve", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionResponseDto.class)))),
			@ApiResponse(responseCode = "404", description = "El cliente o producto no existen", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Errores en los campos del request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
	})
	@PostMapping(path = CREATE)
	public ResponseEntity<TransactionResponseDto> createTransacctions(
			@RequestBody TransactionRequestDto transactionRequestDto) {
		return new ResponseEntity<>(
				new TransactionResponseDto(
						transactionsService.createTransaction(transactionRequestDto.toTransaction())),
				HttpStatus.OK);
	}

	@Operation(summary = "Busca transacciones por de un cliente medio de un email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Obtiene una transaccion y la devuelve", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionResponseDto.class)))),
			@ApiResponse(responseCode = "404", description = "El cliente no existe", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Errores en los campos del request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
	})
	@GetMapping(path = GET_BY_CLIENT_EMAIL)
	public ResponseEntity<List<TransactionResponseDto>> findByClientEmail(
			@RequestParam(required = true) @Email String email,
			@RequestParam(defaultValue = "false") Boolean approved) {
		return new ResponseEntity<>(
				transactionsService.findByClientEmail(email, approved).stream()
						.map(TransactionResponseDto::new)
						.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@Operation(summary = "Busca transacciones todas las transacciones, y filtra por aprobadas o no")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Obtiene una transaccion y la devuelve", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionResponseDto.class)))),
			@ApiResponse(responseCode = "400", description = "Errores en los campos del request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
	})
	@GetMapping(path = GET_ALL)
	public ResponseEntity<List<TransactionResponseDto>> findByApproved(
			@RequestParam(defaultValue = "false") Boolean approved) {
		return new ResponseEntity<>(
				transactionsService.findByApproved(approved).stream().map(TransactionResponseDto::new)
						.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@Operation(summary = "Aprueba transacciones por de un los ids")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Obtiene una resultado clave valor, con id y resultado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionResponseDto.class)))),
			@ApiResponse(responseCode = "400", description = "Errores en los campos del request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
	})
	@PostMapping(path = APPROVED)
	public ResponseEntity<Map<Integer, String>> approvedTransacctions(@RequestBody List<Integer> ids) {
		return new ResponseEntity<>(
				transactionsService.approvedTransacctions(ids),
				HttpStatus.OK);
	}
}
