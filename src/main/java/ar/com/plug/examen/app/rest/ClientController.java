package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Log4j2
@Tag(name = "Clients")
public class ClientController
{

	private final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService)
	{
		this.clientService = clientService;
	}

	@Operation(summary = "Gets a paginated list of clients")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PageDto.class)
			)
		}
	)
	@GetMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> allClients(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size)
	{
		return ResponseEntity.ok(this.clientService.getAllClients(page, size));
	}

	@Operation(summary = "Gets a paginated list of active clients")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PageDto.class)
			)
		}
	)
	@GetMapping(value = "/clients/active", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> activeClients(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size)
	{
		return ResponseEntity.ok(this.clientService.getActiveClientsPageable(page, size));
	}

	@Operation(summary = "Gets client by its document number")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Client.class)
			)
		}
	)
	@GetMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> clientByDocument(@RequestParam String document)
	{
		return ResponseEntity.ok(this.clientService.getClientByDocumentNumber(document));
	}

	@Operation(summary = "Gets client by its id")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Client.class)
			)
		}
	)
	@GetMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> clientByDocument(@PathVariable Long id)
	{
		return ResponseEntity.ok(this.clientService.getClientById(id));
	}
}
