package ar.com.plug.examen.app.rest;

import javax.validation.Valid;

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

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> all() { 
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> newProduct(@Valid @RequestBody Product product) {
		return new ResponseEntity<>(productService.add(product), HttpStatus.OK);

	}

	@GetMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> one(@PathVariable long id) {
		return new ResponseEntity<>(productService.getOne(id), HttpStatus.OK);
	}

	@PutMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> update(@RequestBody Product product) {
		return new ResponseEntity<>(productService.modify(product), HttpStatus.OK);
		
	}

	@DeleteMapping(path = "{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> delete(@PathVariable long id) {
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
