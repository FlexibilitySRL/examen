package ar.com.flexibility.examen.app.rest;

import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService productService;


	@GetMapping("/all")
	public ResponseEntity<Iterable<Product>> getAllProducts() {
		Iterable<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<Page<Product>> getProducts(Pageable pageable) {
		Page<Product> products = productService.getProducts(pageable);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
		Product product = productService.getProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		return new ResponseEntity<>(createdProduct, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable("productId") Long productId) {
		String message = productService.deleteProduct(productId);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
