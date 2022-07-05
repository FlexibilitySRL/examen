package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.app.dtoResponse.ProductResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListProductResponseDTO;
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

public interface IProductController {

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Creates a new product", notes = "Creates a new product")
    @PostMapping(value = { "/add_product" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponseDTO> addProductRequest(@ApiParam(name = "product_request", value = "New product request", required = true) @Valid ProductDTO request) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Update a product", notes = "Update a product")
    @PutMapping(value = { "/update_product" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponseDTO> updateProductRequest(@ApiParam(name = "product_request", value = "Update product request", required = true) @Valid ProductDTO request) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "Remove a product", notes = "Remove a product")
    @DeleteMapping(value = { "/remove_product/{productCode}" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponseDTO> removeProductRequest(@ApiParam(name = "product_request", value = "Remove product request", required = true) @PathVariable String productCode) throws Exception;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ListProductResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class) })
    @ApiOperation(value = "List of products", notes = "List of products")
    @GetMapping(value = { "/list_product" }, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ListProductResponseDTO> listProductRequest() throws Exception;
}
