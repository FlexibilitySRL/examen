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

import ar.com.flexibility.examen.domain.dto.ClientDTO;
import ar.com.flexibility.examen.domain.dto.LegalClientDTO;
import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.dto.PageRequestDTO;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.service.ProductService;

@RestController
@RequestMapping(path = "/")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("products")
	public ResponseEntity<List<ObjectDTO<ProductDTO>>> listProducts(@RequestBody PageRequestDTO pageRequestDTO) {
		return new ResponseEntity<List<ObjectDTO<ProductDTO>>>(this.productService.listProducts(pageRequestDTO), HttpStatus.OK);
	}
	
	@GetMapping("product/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
		return new ResponseEntity<ProductDTO>(this.productService.getProduct(id), HttpStatus.OK);
	}
	
	@PostMapping("product")
	public ResponseEntity<Long> addProduct(@RequestBody ProductDTO productDTO) {
		return new ResponseEntity<Long>(this.productService.addProduct(productDTO), HttpStatus.OK);
	}
	
	@PutMapping("product/{id}")
	public ResponseEntity<Void> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
		this.productService.updateProduct(id, productDTO);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("product/{id}")
	public ResponseEntity<Void> removeProduct(@PathVariable("id") Long id) {
		this.productService.removeProduct(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
