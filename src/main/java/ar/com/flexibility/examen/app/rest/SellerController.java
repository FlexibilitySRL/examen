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

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.service.ProcessSellerService;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {
	
	private final Logger log = LoggerFactory.getLogger(SellerController.class);

	@Autowired
	private ProcessSellerService sellerService;

	/**
	 * Seller create operation
	 * @param sellerApi
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody SellerApi sellerApi) {
		String mensaje = "";
		try {
			log.info("Adding a new seller...");
			return new ResponseEntity<SellerApi>(sellerService.create(sellerApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error creating a seller.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Seller update operation
	 * @param sellerId
	 * @param sellerApi
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestParam("id") Long sellerId, @RequestBody SellerApi sellerApi) {
		String mensaje = "";
		log.info("Updating a seller...");
		try {
			return new ResponseEntity<SellerApi>(sellerService.update(sellerId, sellerApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error updating a seller.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Seller delete operation
	 * @param sellerApi
	 * @return
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody SellerApi sellerApi) {
		String mensaje = "";
		log.info("Deleting seller...");
		try {
			return new ResponseEntity<String>(sellerService.delete(sellerApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error deleting the seller.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
