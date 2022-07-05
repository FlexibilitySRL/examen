package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.ErrorResponse;
import ar.com.plug.examen.app.dtoResponse.ClientResponseDTO;
import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.dtoResponse.ListClientResponseDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface IClientController {

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ClientResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Creates a new client", notes = "Creates a new client")
    @PostMapping(value = { "/add_client" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClientResponseDTO> addClientRequest(@ApiParam(name = "client_request", value = "New client request", required = true) @Valid ClientDTO request) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ClientResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Update a client", notes = "Update a client")
    @PutMapping(value = { "/update_client" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClientResponseDTO> updateClientRequest(@ApiParam(name = "client_request", value = "Update client request", required = true) @Valid ClientDTO request) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ClientResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Remove a client", notes = "Remove a client")
    @DeleteMapping(value = { "/remove_client/{document}" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClientResponseDTO> removeClientRequest(@ApiParam(name = "client_request", value = "Remove client request", required = true) @PathVariable Integer document) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ListClientResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "List of clients", notes = "List of clients")
    @GetMapping(value = { "/list_client" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ListClientResponseDTO> listClientRequest() throws Exception;

}
