package ar.com.plug.examen.app.rest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping(path = "/products", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllProduct() {
		return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/products/{productid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteProduct(@PathVariable Long productid) {
		service.deleteProduct(productid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/products/{productid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getProductById(@PathVariable Long productid) {
		return new ResponseEntity<>(service.getProductById(productid), HttpStatus.OK);

	}

	@PostMapping(path = "/products", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
		return new ResponseEntity<>(service.createProduct(product), HttpStatus.CREATED);
	}

	@PutMapping(path = "/products", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		return new ResponseEntity<>(service.updateProduct(product), HttpStatus.OK);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> "field " + x.getField() + " " + x.getDefaultMessage()).collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
