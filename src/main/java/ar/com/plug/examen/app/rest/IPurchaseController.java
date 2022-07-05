package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.PurchaseDTO;
import ar.com.plug.examen.app.dtoResponse.ListPurchaseResponseDTO;
import ar.com.plug.examen.app.dtoResponse.PurchaseResponseDTO;
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
import org.springframework.web.bind.annotation.ResponseStatus;

public interface IPurchaseController {

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PurchaseResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Creates a new purchase", notes = "Creates a new purchase")
    @PostMapping(value = { "/add_purchase" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseResponseDTO> addPurchaseRequest(@ApiParam(name = "purchase_request", value = "New purchase request", required = true) @Valid PurchaseDTO request) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PurchaseResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Approve a purchase", notes = "Approve a purchase")
    @PutMapping(value = { "/approve_purchase/{receiptId}" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseResponseDTO> approvePurchaseRequest(@ApiParam(name = "purchase_request", value = "Approve purchase request", required = true) @PathVariable Long receiptId) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PurchaseResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Cancel a purchase", notes = "Cancel a purchase")
    @PutMapping(value = { "/cancel_purchase/{receiptId}" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseResponseDTO> cancelPurchaseRequest(@ApiParam(name = "purchase_request", value = "Cancel purchase request", required = true) @PathVariable Long receiptId) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ListPurchaseResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "List of purchases", notes = "List of purchases")
    @GetMapping(value = { "/list_purchase" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ListPurchaseResponseDTO> listPurchaseRequest() throws Exception;
}
