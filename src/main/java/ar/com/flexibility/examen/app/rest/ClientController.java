package ar.com.flexibility.examen.app.rest;

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

import ar.com.flexibility.examen.app.api.ExceptionApi;
import ar.com.flexibility.examen.app.api.OrderApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exception.ClientNotFoundException;
import ar.com.flexibility.examen.domain.exception.EmptyOrderException;
import ar.com.flexibility.examen.domain.exception.InsufficientBalanceException;
import ar.com.flexibility.examen.domain.exception.NegativeAmountException;
import ar.com.flexibility.examen.domain.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.exception.ProductWithoutStock;
import ar.com.flexibility.examen.domain.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/client")
@Api(tags="client")
public class ClientController  {
	
	@Autowired
    private ClientService clientService;
	
	@PutMapping("/{idClient}/{amount}")
	@ApiOperation(value= "Update balance", notes = "Service to update the value of balance client")
	@ApiResponses(value= {@ApiResponse(code= 200, message= "The operation was successfully"),
    @ApiResponse(code= 404, message="Client not found"),
    @ApiResponse(code= 407, message="Error during the processing of update balance")})
	public ResponseEntity<?> updateBalance(@PathVariable("idClient") Long idClient, @PathVariable("amount") double amount) {
		try {
			clientService.updateBalance(idClient, amount);
		} catch (NegativeAmountException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ExceptionApi(e));
		} catch (ClientNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionApi(e));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/allPurchases/{idClient}")
	@ApiOperation(value= "Find all purchase of client", notes = "Service to list all purchase of specific client")
	@ApiResponses(value= {@ApiResponse(code= 200, message= "The operation was successfully"),
    @ApiResponse(code= 404, message="Client not found")})
	public ResponseEntity<?> findAllPurchaseOfClient(@PathVariable("idClient") Long idClient) {
		try {
			return new ResponseEntity<>(clientService.findAllPurchaseOfClient(idClient),HttpStatus.OK);
		} catch (ClientNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionApi(e));
		}
	}
	
	
	@PostMapping("/{idClient}")
	@ApiOperation(value= "Make a purchase", notes = "Service to make a purchase")
	@ApiResponses(value= {@ApiResponse(code= 200, message= "The operation was successfully"),
    @ApiResponse(code= 404, message="Client not found"),
    @ApiResponse(code= 407, message="Errors during the processing of the purchase")})
	public ResponseEntity<?> processPurchase(@PathVariable("idClient") Long idClient, @RequestBody List<OrderApi> orders){
		try {
			clientService.processPurchase(idClient,orders);
		} catch (ProductNotFoundException | ProductWithoutStock | InsufficientBalanceException | EmptyOrderException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ExceptionApi(e));
		} catch (ClientNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionApi(e));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{idClient}")
	@ApiOperation(value= "Get data client", notes = "Service to get data of client")
	@ApiResponses(value= {@ApiResponse(code= 200, message= "The operation was successfully"),
    @ApiResponse(code= 404, message="Client not found")})
	public ResponseEntity<?> getDataClient(@PathVariable("idClient") Long idClient){
		
		try {
			return new ResponseEntity<>(clientService.getClientApi(idClient),HttpStatus.OK);
		} catch (ClientNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionApi(e));
		}
	}
	
    @GetMapping("/products")
    @ApiOperation(value= "All products", notes = "Service to list all products")
    @ApiResponses(value= {@ApiResponse(code= 200, message= "OK")})
    public ResponseEntity<List<ProductApi>> getAllProducts(){
    	return new ResponseEntity<>(clientService.findAllProducts(),HttpStatus.OK);
    }
	
	
	
	
	
}
