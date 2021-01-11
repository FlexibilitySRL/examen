package ar.com.plug.examen.app.rest;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.service.ProductService;

@RestController
@RequestMapping(path="/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ProductApi> createProduct(@RequestBody ProductApi ProductApi){
		
		return new ResponseEntity<>(productService.createProduct(ProductApi), HttpStatus.CREATED);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductApi> getProduct(@PathVariable Long id){
		
		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<ProductApi>> listAllProducts(){
		
		return new ResponseEntity<>(productService.listAllProducts(), HttpStatus.OK);
		
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity removeProduct(@PathVariable Long id) {
		
		productService.removeProduct(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductApi> updateProduct(@PathVariable Long id, @RequestBody ProductApi ProductApi){
		
		return new ResponseEntity<>(productService.updateProduct(id, ProductApi), HttpStatus.CREATED);
		
	}
	
}
