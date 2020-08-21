package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.service.ProcessProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

	private final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProcessProductService productService;

	/**
	 * Product create operation
	 * @param productApi
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductApi productApi) {
		String mensaje = "";
		try {
			log.info("Adding a new product...");
			return new ResponseEntity<ProductApi>(productService.create(productApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error creating a product.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Product update operation
	 * @param productId
	 * @param productApi
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestParam("id") Long productId, @RequestBody ProductApi productApi) {
		String mensaje = "";
		log.info("Updating a product...");
		try {
			return new ResponseEntity<ProductApi>(productService.update(productId, productApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error updating a product.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	/**
//	 * Operation 
//	 * @param productId
//	 * @param productApi
//	 * @return
//	 */
//	@GetMapping("/search")
//	public ResponseEntity<?> search(@RequestParam("id") Long productId, @RequestBody ProductApi productApi) {
//		String mensaje = "";
//		log.info("Searching a product...");
//		try {
//			return new ResponseEntity<ProductApi>(productService.search(productId), HttpStatus.OK);
//		} catch (Exception e) {
//			mensaje = "There was an error searching a product.";
//			log.error(mensaje, e.getMessage());
//			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody ProductApi productApi) {
		String mensaje = "";
		log.info("Deleting product...");
		try {
			return new ResponseEntity<String>(productService.delete(productApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error deleting the product.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
