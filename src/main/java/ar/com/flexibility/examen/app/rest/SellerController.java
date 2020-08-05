package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.app.api.response.SellerApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.service.SellerService;

import java.util.List;

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

@RestController
@RequestMapping(path = "/seller")
public class SellerController {

    // ---------------
    // Attributes
    // ---------------
    @Autowired
    private MessagesProps messages;
	@Autowired
	private SellerService sellerService;

    // ---------------
    // Methods
    // ---------------
	@DeleteMapping ("/{identifier}")
	public ResponseEntity<?> deleteSeller (@PathVariable String identifier) {
		try {
			// To delete/remove a seller
			this.sellerService.delete (identifier);
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping ("/{identifier}")
	public ResponseEntity<?> getSeller (@PathVariable String identifier) {
		try {
			// To get a seller by its identifier
			SellerApiResponse seller = this.sellerService.get (identifier);
			
			return new ResponseEntity<>(seller, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> list () {
		try {
			// To get a list of sellers
			List<SellerApiResponse> data = this.sellerService.list ();
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> newSeller (@RequestBody SellerApi seller) {
		try {
			// To save a new seller
			this.sellerService.save (seller.getIdentifier(), 
					seller.getName(), seller.getSurname());
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping ("/{identifier}")
	public ResponseEntity<?> updateSeller (@RequestBody SellerApi seller, @PathVariable String identifier) {
		try {
			// To update a seller
			this.sellerService.update (identifier, seller.getIdentifier(), 
					seller.getName(), seller.getSurname());
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
