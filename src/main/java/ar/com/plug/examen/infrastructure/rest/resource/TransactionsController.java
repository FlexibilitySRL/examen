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
import ar.com.plug.examen.infrastructure.rest.dto.TransactionRequestDto;
import ar.com.plug.examen.infrastructure.rest.dto.TransactionResponseDto;
import lombok.RequiredArgsConstructor;

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

	@PostMapping(path = CREATE)
	public ResponseEntity<TransactionResponseDto> createTransacctions(
			@RequestBody TransactionRequestDto transactionRequestDto) {
		return new ResponseEntity<>(
				new TransactionResponseDto(
						transactionsService.createTransaction(transactionRequestDto.toTransaction())),
				HttpStatus.OK);
	}

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

	@GetMapping(path = GET_ALL)
	public ResponseEntity<List<TransactionResponseDto>> findByApproved(
			@RequestParam(defaultValue = "false") Boolean approved) {
		return new ResponseEntity<>(
				transactionsService.findByApproved(approved).stream().map(TransactionResponseDto::new)
						.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PostMapping(path = APPROVED)
	public ResponseEntity<Map<String, String>> approvedTransacctions(@RequestBody List<String> ids) {
		return new ResponseEntity<>(
				transactionsService.approvedTransacctions(ids),
				HttpStatus.OK);
	}
}
