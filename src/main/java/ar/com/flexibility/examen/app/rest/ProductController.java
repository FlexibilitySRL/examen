package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.response.ProductApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/product")
public class ProductController extends CustomController {

    // ---------------
    // Attributes
    // ---------------
    @Autowired
    private MessagesProps messages;
	@Autowired
	private ProductService productService;

    // ---------------
    // Methods
    // ---------------
	@DeleteMapping (path = "/{code}", produces = "application/json")
	@ApiOperation(value = "Removes a product")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<Object> deleteProduct (@PathVariable String code) {
		try {
			// To save a new product
			this.productService.deleteProduct (code);
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (path = "/{code}", produces = "application/json")
	@ApiOperation(value = "Gets a product")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<Object> getProduct (@PathVariable String code) {
		try {
			// To save a new product
			ProductApiResponse product = this.productService.getProduct (code);
			
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (produces = "application/json")
	@ApiOperation(value = "List of products")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<Object> list () {
		try {
			// To save a new product
			List<ProductApiResponse> data = this.productService.list ();
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping (produces = "application/json")
	@ApiOperation(value = "Inserts a new product")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<Object> newProduct (@RequestBody ProductApi product) {
		try {
			// To save a new product
			this.productService.newProduct(product.getCode(), 
					product.getName(), product.getAmount(), product.getPrice());
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping (path = "/{code}", produces = "application/json")
	@ApiOperation(value = "Updates a new product")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<Object> updateProduct (@RequestBody ProductApi product, @PathVariable String code) {
		try {
			// To save a new product
			this.productService.updateProduct(code, product.getCode(), 
					product.getName(), product.getAmount(), product.getPrice());
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
