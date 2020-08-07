package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SaleApi;
import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.service.SaleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
public class SaleController extends CustomController {

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
	@PutMapping(path = "/{code}", produces = "application/json")
	@ApiOperation(value = "Approves a sale")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public ResponseEntity<Object> approveSale(@ApiParam("Sale code") @PathVariable String code) {
		try {
			// To update a sale
			this.saleService.approveSale(code);

			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{code}", produces = "application/json")
	@ApiOperation(value = "Gets a sale")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public ResponseEntity<Object> getSale(@ApiParam("Sale code") @PathVariable String code) {
		try {
			SaleApiResponse sale = this.saleService.getSale(code);

			return new ResponseEntity<>(sale, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/status/{status}", produces = "application/json")
	@ApiOperation(value = "List of sales by status. Two options: 'APROBADO' and 'PENDIENTE'")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public ResponseEntity<Object> getSalesByStatus(@ApiParam("Sale status") @PathVariable String status) {
		try {
			// To get sales by status
			List<SaleApiResponse> data = this.saleService.getSalesByStatus(status);

			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(produces = "application/json")
	@ApiOperation(value = "List of sales")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public ResponseEntity<Object> list() {
		try {
			// To get list of sales
			List<SaleApiResponse> data = this.saleService.list();

			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(produces = "application/json")
	@ApiOperation(value = "Inserts a new sale")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public ResponseEntity<Object> newSale(@ApiParam("Sale data") @RequestBody SaleApi sale) {
		try {
			// To save a new sale
			this.saleService.newSale(sale.getCode(), sale.getClientIdentifier(), sale.getSellerIdentifier(),
					sale.getProductCode(), sale.getProductAmount());

			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
