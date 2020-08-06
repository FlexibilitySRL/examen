package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.app.api.response.SellerApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.service.SellerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
public class SellerController extends CustomController {

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
	@DeleteMapping (path = "/{identifier}", produces = "application/json")
	@ApiOperation(value = "Removes a seller")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> deleteSeller (@PathVariable String identifier) {
		try {
			// To delete/remove a seller
			this.sellerService.delete (identifier);
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (path = "/{identifier}", produces = "application/json")
	@ApiOperation(value = "Gets a seller")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> getSeller (@PathVariable String identifier) {
		try {
			// To get a seller by its identifier
			SellerApiResponse seller = this.sellerService.get (identifier);
			
			return new ResponseEntity<>(seller, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (produces = "application/json")
	@ApiOperation(value = "List of sellers")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> list () {
		try {
			// To get a list of sellers
			List<SellerApiResponse> data = this.sellerService.list ();
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping (produces = "application/json")
	@ApiOperation(value = "Inserts a new seller")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> newSeller (@RequestBody SellerApi seller) {
		try {
			// To save a new seller
			this.sellerService.save (seller.getIdentifier(), 
					seller.getName(), seller.getSurname());
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping (path = "/{identifier}", produces = "application/json")
	@ApiOperation(value = "Updates a seller")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> updateSeller (@RequestBody SellerApi seller, @PathVariable String identifier) {
		try {
			// To update a seller
			this.sellerService.update (identifier, seller.getIdentifier(), 
					seller.getName(), seller.getSurname());
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
