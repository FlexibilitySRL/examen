package ar.com.flexibility.examen.app.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.exception.GenericException;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Product>> findAll() throws GenericException {
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Product> find(@PathVariable("id") Long id) throws GenericException {
		return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
	}

	@PostMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Product> save(@Valid @NotNull @RequestBody Product product) throws GenericException {
		return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
	}

	@PutMapping(path = "{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Product> update(@PathVariable("id") Long id, @Valid @NotNull @RequestBody Product product)
			throws GenericException {
		product.setId(id);
		return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Product> remove(@PathVariable("id") Long id) throws GenericException {
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}