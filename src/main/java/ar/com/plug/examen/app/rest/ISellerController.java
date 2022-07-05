package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.app.dtoResponse.ListSellerResponseDTO;
import ar.com.plug.examen.app.dtoResponse.SellerResponseDTO;
import ar.com.plug.examen.domain.model.ErrorResponse;
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

public interface ISellerController {

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SellerResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Creates a new seller", notes = "Creates a new seller")
    @PostMapping(value = { "/add_seller" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SellerResponseDTO> addSellerRequest(@ApiParam(name = "seller_request", value = "New seller request", required = true) @Valid SellerDTO request) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SellerResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Update a seller", notes = "Update a seller")
    @PutMapping(value = { "/update_seller" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SellerResponseDTO> updateSellerRequest(@ApiParam(name = "seller_request", value = "Update seller request", required = true) @Valid SellerDTO request) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SellerResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Remove a seller", notes = "Remove a seller")
    @DeleteMapping(value = { "/remove_seller/{document}" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SellerResponseDTO> removeSellerRequest(@ApiParam(name = "seller_request", value = "Remove seller request", required = true) @PathVariable Integer document) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ListSellerResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "List of sellers", notes = "List of sellers")
    @GetMapping(value = { "/list_seller" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ListSellerResponseDTO> listSellerRequest() throws Exception;
}
