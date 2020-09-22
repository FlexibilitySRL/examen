package ar.com.flexibility.examen.app.rest;

import java.util.Objects;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") long id) {
		ProductDTO Product = productService.getProduct(id);
		if (Objects.isNull(Product)) {
			logger.error("Product with id {} not found.", id);
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
		logger.info("Product with id {} found.", id);
		return new ResponseEntity<ProductDTO>(Product, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO Product, BindingResult result) {
		if (result.hasErrors()) {
			logger.error("Product invalid.", Product);
			return new ResponseEntity<ProductDTO>(HttpStatus.BAD_REQUEST);
		}
		ProductDTO productDB = productService.createProduct(Product);
		logger.info("Product with id {} created.", productDB.getId());
		return new ResponseEntity<ProductDTO>(productDB, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") long id, @RequestBody ProductDTO Product) {
		Product.setId(id);
		ProductDTO currentProduct = productService.updateProduct(Product);
		if (Objects.isNull(currentProduct)) {
			logger.error("Product with id {} not found.", id);
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
		logger.info("Product with id {} update.", id);
		return new ResponseEntity<ProductDTO>(currentProduct, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long id) {
		ProductDTO Product = productService.deleteProduct(id);
		if (Objects.isNull(Product)) {
			logger.error("Product with id {} not found.", id);
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
		logger.info("Product with id {} deleted.", id);
		return ResponseEntity.ok(Product);
	}
}
