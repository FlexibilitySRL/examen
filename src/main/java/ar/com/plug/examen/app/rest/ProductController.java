package ar.com.plug.examen.app.rest;

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

import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.examen.dto.ProductDto;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl service;
	
	

	// Gets every stored product
	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getProducts(){
		return new ResponseEntity<List<ProductDto>>(service.findAll(), HttpStatus.OK);
	}
	
	
	// Gets a specific product from an id
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) throws Exception{
		return new ResponseEntity<ProductDto>(service.findById(id), HttpStatus.OK);
	}
	
	
	// Stores a new product
	@PostMapping("/create")
	public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto product){
		return new ResponseEntity<>(service.save(product), HttpStatus.OK);
	}
	
	
	// Updates an existing product
	@PutMapping("/update")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product) throws Exception{
		return new ResponseEntity<ProductDto>(service.update(product), HttpStatus.OK);
	}
	
	
	
	// Deletes a specific product from an id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
