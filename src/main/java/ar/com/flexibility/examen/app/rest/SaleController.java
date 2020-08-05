package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SaleApi;
import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.service.SaleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {

    // ---------------
    // Attributes
    // ---------------
    @Autowired
    private MessagesProps messages;
	@Autowired
	private SaleService saleService;

    // ---------------
    // Methods
    // ---------------
	@PutMapping ("/{code}")
	public ResponseEntity<?> approveSale (@PathVariable String code) {
		try {
			// To update a sale
			this.saleService.approveSale (code);
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping ("/{code}")
	public ResponseEntity<?> getSale (@PathVariable String code) {
		try {
			SaleApiResponse sale = this.saleService.getSale (code);
			
			return new ResponseEntity<>(sale, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping ("/status/{status}")
	public ResponseEntity<?> getSalesByStatus (@PathVariable String status) {
		try {
			// To get sales by status
			List<SaleApiResponse> data = this.saleService.getSalesByStatus (status);
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> list () {
		try {
			// To get list of sales
			List<SaleApiResponse> data = this.saleService.list ();
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> newSale (@RequestBody SaleApi sale) {
		try {
			// To save a new sale
			this.saleService.newSale (sale.getCode(), sale.getClientIdentifier(), sale.getSellerIdentifier(),
					sale.getProductCode(), sale.getProductAmount());
			
			return new ResponseEntity<>(messages.getSucessTransaction(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
