package ar.com.plug.examen.app.rest;

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
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	private SellerService service;
	
	@GetMapping(path="/sellers",produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllSeller() {
		return new ResponseEntity<>(service.getAllSeller(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/sellers/{sellerid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteProduct(@PathVariable Long sellerid) {
		service.deleteSeller(sellerid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/sellers/{sellerid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Seller> getSellerById(@PathVariable Long sellerid) {
		return  new ResponseEntity<Seller>(service.getSellerById(sellerid), HttpStatus.OK);

	}

	@PostMapping(path = "/sellers", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seller> createProduct(@RequestBody Seller seller) {
		return  new ResponseEntity<Seller>(service.create(seller), HttpStatus.CREATED);
	}

	@PutMapping(path = "/sellers", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seller> updateProduct(@RequestBody Seller seller) {
		return new ResponseEntity<Seller>(service.update(seller), HttpStatus.OK);
	}
	
}
