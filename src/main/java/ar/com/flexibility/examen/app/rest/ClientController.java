package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/client")
@Api(tags="client")
public class ClientController {
	
	@Autowired
    private ClientService clientService;
	
	
	
	@PutMapping("/{idClient}/{amount}")
	@ApiOperation(value= "Update balance", notes = "Service to update the value of balance client")
	@ApiResponses(value= {@ApiResponse(code= 200, message= "The operation was successfully"),
    @ApiResponse(code= 400, message="Client not found")})
	public ResponseEntity<ClientApi> updateBalance(@PathVariable("idClient") Long idClient, @PathVariable("amount") double amount) {
		Client client = this.clientService.findById(idClient);
		if(client == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		client.setBalance(client.getBalance() + amount);
		ClientApi clientApi = clientService.saveOrUpdate(client);
		return new ResponseEntity<>(clientApi,HttpStatus.OK);
	}
	
	@GetMapping("/{idClient}")
	@ApiOperation(value= "find all purchase of client", notes = "Service to list all purchase of client")
	@ApiResponses(value= {@ApiResponse(code= 200, message= "The operation was successfully"),
    @ApiResponse(code= 400, message="Client not found")})
	public ResponseEntity<List<PurchaseApi>> findAllPurchaseOfClient(@PathVariable("idClient") Long idClient) {
		Client client = this.clientService.findById(idClient);
		if(client == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		return new ResponseEntity<>(clientService.findAllPurchaseOfClient(client),HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
