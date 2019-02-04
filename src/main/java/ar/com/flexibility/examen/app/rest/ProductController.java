package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
@RestController
@RequestMapping(path = "/private/product")
public class ProductController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductService productService;
	
	
	/**
	 * Create a new product
	 * 
	 * @param product
	 * @return Product
	 */
	@PostMapping
	@ApiOperation(value = "Create a new product")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		log.info("Creating a Product");
		
		Product response = productService.create(product);
		log.info("Product created");
		
		return new ResponseEntity<Product>(response, HttpStatus.OK);
	}
	
	/**
	 * Update a Product
	 * 
	 * @param product
	 * @return Product
	 */
	@PutMapping
	@ApiOperation(value = "Update a Product")
	public ResponseEntity<?> update(@RequestBody Product product) {
		log.info("Updating a Product");
		
		Product response = null;
		
		response = productService.update(product);
		
		if (response != null) {
			log.info("Product updated");
			return new ResponseEntity<Product>(response, HttpStatus.OK);
			
		} else {
			log.info("Prodcut don't exists");
			return new ResponseEntity<Message>(new Message("Product don't exists"), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Get a Product by ID
	 * 
	 * @param idProduct
	 * @return Product
	 */
	@GetMapping("/{idProduct}")
	@ApiOperation(value = "Get a Product by ID")
	public ResponseEntity<Product> findOne(@PathVariable Long idProduct) {
		log.info("Getting Product by ID: "+idProduct);
		
		Product response = productService.findById(idProduct);
		
		return new ResponseEntity<Product>(response, HttpStatus.OK);
	}
	
	/**
	 * Get All of Products
	 * 
	 * @return List<Product>
	 */
	@GetMapping
	@ApiOperation(value = "Get All of Products")
	public ResponseEntity<List<Product>> findAll() {
		log.info("Getting All of Products");
		
		List<Product> products = productService.findAll();
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	/**
	 * Delete a Product by ID
	 * 
	 * @param idProduct
	 */
	@DeleteMapping("/{idProduct}")
	@ApiOperation(value = "Delete a Product by ID")
	public void deleteById(@PathVariable Long idProduct) {
		log.info("Deleting a Product by ID: "+idProduct);
		
		productService.deleteById(idProduct);
	}
	
}
