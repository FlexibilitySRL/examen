package ar.com.plug.examen.infrastructure.rest.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.Client;
import ar.com.plug.examen.domain.service.ServiceDomain;
import ar.com.plug.examen.infrastructure.rest.dto.ClientRequestDto;
import ar.com.plug.examen.infrastructure.rest.dto.ClientResponseDto;
import ar.com.plug.examen.infrastructure.rest.dto.ResponseDto;
import ar.com.plug.examen.shared.config.MenssageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Clients", description = "Catálogo de clientes")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = ClientController.PATH)
public class ClientController {

    private final ServiceDomain<Client> clientService;
    private final MenssageResponse menssageResponse;
    public final static String PATH = "/client";
    public final static String CLIENT_BY_FILTER = "all";

    @Operation(summary = "Obtiene un cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente por id", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontro el cliente", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<ClientResponseDto> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(new ClientResponseDto(clientService.findById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una lista de clientes por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes", content = @Content(schema = @Schema(implementation = ClientResponseDto.class)))
    })
    @GetMapping(path = CLIENT_BY_FILTER)
    public ResponseEntity<List<ClientResponseDto>> findAllByFilter(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(required = false, defaultValue = "") String docNumber,
            @RequestParam(required = false, defaultValue = "") String email) {
        return new ResponseEntity<>(clientService.findAllByFilter(Client.builder()
                .name(name)
                .lastName(lastName)
                .docNumber(docNumber)
                .email(email)
                .build()).stream().map(ClientResponseDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "Crea un cliente por medio de un json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crea el cliente y lo devuelve", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientResponseDto.class)))),
            @ApiResponse(responseCode = "409", description = "Ya el cliente existe", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Errores en los campos del request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return new ResponseEntity<>(new ClientResponseDto(clientService.create(clientRequestDto.toClient())),
                HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un cliente por medio de un json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualiza el cliente y lo devuelve", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontro el cliente", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Ya el cliente existe", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Errores en los campos del request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PatchMapping
    public ResponseEntity<ClientResponseDto> upDate(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return new ResponseEntity<>(new ClientResponseDto(clientService.upDate(clientRequestDto.toClient())),
                HttpStatus.OK);
    }

    @Operation(summary = "Elimina un cliente por por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elimina el cliente ", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontro el cliente", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Errores en el request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> remove(@RequestParam Integer id) {
        clientService.remove(id);
        return new ResponseEntity<>(ResponseDto.builder()
                .code(MenssageResponse.OK)
                .message(menssageResponse.getMessages().get(MenssageResponse.OK))
                .build(), HttpStatus.OK);
    }

}
