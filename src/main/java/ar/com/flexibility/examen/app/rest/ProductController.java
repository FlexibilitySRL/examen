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

@RestController
@RequestMapping(path = "/product")
public class ProductController {

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
	@DeleteMapping ("/{code}")
	public ResponseEntity<?> deleteProduct (@PathVariable String code) {
		try {
			// To save a new product
			this.productService.deleteProduct (code);
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping ("/{code}")
	public ResponseEntity<?> getProduct (@PathVariable String code) {
		try {
			// To save a new product
			ProductApiResponse product = this.productService.getProduct (code);
			
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> list () {
		try {
			// To save a new product
			List<ProductApiResponse> data = this.productService.list ();
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> newProduct (@RequestBody ProductApi product) {
		try {
			// To save a new product
			this.productService.newProduct(product.getCode(), 
					product.getName(), product.getAmount(), product.getPrice());
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping ("/{code}")
	public ResponseEntity<?> updateProduct (@RequestBody ProductApi product, @PathVariable String code) {
		try {
			// To save a new product
			this.productService.updateProduct(code, product.getCode(), 
					product.getName(), product.getAmount(), product.getPrice());
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
