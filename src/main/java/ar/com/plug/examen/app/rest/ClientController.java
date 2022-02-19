package ar.com.plug.examen.app.rest;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.ClientDto;
import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
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
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
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
	public ResponseEntity<?> clientByDocument(@Parameter(description = "Client's document number", example = "3509091")
											@RequestParam String document)
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
	public ResponseEntity<?> clientByDocument(@Parameter(description = "Client's internal id", example = "2")
												@PathVariable Long id)
	{
		return ResponseEntity.ok(this.clientService.getClientById(id));
	}

	@Operation(summary = "Creates a new client.")
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
	@PostMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createClient(@RequestBody @Valid ClientDto clientDto) throws ValidationException
	{
		return ResponseEntity.ok(this.clientService.saveClient(clientDto));
	}

	@Operation(summary = "Updates the information of a client.")
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
	@PutMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateClient(@Parameter(description = "Client's internal id", example = "2")
											@PathVariable Long id,
											@RequestBody @Valid ClientDto dto) throws ValidationException
	{
		return ResponseEntity.ok(this.clientService.updateClient(id, dto));
	}

	@Operation(summary = "Delete a client logically by inactivate him.")
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
	@PutMapping(value = "/client/inactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inactivateClient(@Parameter(description = "Client's internal id", example = "2")
											@PathVariable Long id) throws ValidationException
	{
		return ResponseEntity.ok(this.clientService.inactivateClient(id));
	}

	@Operation(summary = "Delete a client physically from the database")
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
	@DeleteMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteClient (@Parameter(description = "Client's internal id", example = "2")
											@PathVariable Long id) throws ValidationException
	{
		return ResponseEntity.ok(this.clientService.deleteClient(id));
	}
}
