package ar.com.flexibility.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.rest.dto.ProductRequestDto;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/findProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> findProduct(@RequestParam String productName) { 
		return new ResponseEntity<Product>(productService.findProduct(productName), HttpStatus.OK);
	}
	
	@PostMapping(value = "/createProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createProduct(@RequestBody ProductRequestDto dto) {
		productService.createProduct(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteProduct/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateProduct(@RequestBody ProductRequestDto dto) {
		productService.updateProduct(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
