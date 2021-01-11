package ar.com.plug.examen.app.rest;

import java.util.List;

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

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.service.SellerService;

@RestController
@RequestMapping(path="/seller", produces = MediaType.APPLICATION_JSON_VALUE)
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SellerApi> createSeller(@RequestBody SellerApi sellerApi){
		
		return new ResponseEntity<>(sellerService.createSeller(sellerApi), HttpStatus.CREATED);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<SellerApi> getSeller(@PathVariable Long id){
		
		return new ResponseEntity<>(sellerService.getSellerById(id), HttpStatus.OK);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<SellerApi>>  listAllSellers(){
		
		return new ResponseEntity<>(sellerService.listAllSellers(), HttpStatus.OK);
		
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity removeSeller(@PathVariable Long id) {
		
		sellerService.removeSeller(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellerApi> updateSeller(@PathVariable Long id, @RequestBody SellerApi sellerApi){
		
		return new ResponseEntity<>(sellerService.updateSeller(id, sellerApi), HttpStatus.CREATED);
		
	}
	
}
