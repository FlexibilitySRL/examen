package ar.com.flexibility.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.ExceptionApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exception.ProductNameNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.exception.ProductPriceNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.ProductStockNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.SellerNotFoundException;
import ar.com.flexibility.examen.domain.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/seller")
@Api(tags="seller")
public class SellerController {
	
    @Autowired
    private SellerService productService;
    
    
    @GetMapping("/{idSeller}")
    @ApiOperation(value= "All products of seller", notes = "Service to list of product of seller")
    @ApiResponses(value= {@ApiResponse(code= 200, message= "OK"),
    		@ApiResponse(code= 404, message="Seller not found")})
    public ResponseEntity<?> getAllProductsOfSeller(@PathVariable("idSeller") Long idSeller){
    	try {
			return new ResponseEntity<>(productService.getProductsOfSeller(idSeller),HttpStatus.OK);
		} catch (SellerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionApi(e));
		}
    }
    
    @PostMapping
    @ApiOperation(value= "Add new product", notes = "Service to add new product")
    @ApiResponses(value= {@ApiResponse(code= 200, message= "Product added successfully"),
    		@ApiResponse(code= 404, message="Seller not found"),
    		@ApiResponse(code= 407, message="Errors during the processing of add new product")})
    public ResponseEntity<?> addNewProduct(@RequestBody ProductApi productApi) {
    	
    	try {
    		productService.createNewProductToSeller(productApi);
		} catch (ProductNameNotAcceptedException | ProductPriceNotAcceptedException
				 | ProductStockNotAcceptedException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ExceptionApi(e));
		} catch (SellerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionApi(e));
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping
    @ApiOperation(value= "Update product", notes = "Service to update a product")
    @ApiResponses(value= {@ApiResponse(code= 200, message= "Product was update successfully"),
    		@ApiResponse(code= 404, message="Seller not found"),
    		@ApiResponse(code= 407, message="Errors during the processing of update product")})
    public ResponseEntity<?> updateProduct(@RequestBody ProductApi productApi) {
    	
    	try {
    		productService.updateProductOfSeller(productApi);
		} catch (ProductNameNotAcceptedException | ProductPriceNotAcceptedException
				 | ProductStockNotAcceptedException | ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ExceptionApi(e));
		} catch (SellerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionApi(e));
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
