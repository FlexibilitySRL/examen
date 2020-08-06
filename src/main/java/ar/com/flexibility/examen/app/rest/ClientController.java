package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.response.ClientApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.service.ClientService;
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
@RequestMapping(path = "/client", name = "Clientes")
public class ClientController extends CustomController {

    // ---------------
    // Attributes
    // ---------------
    @Autowired
    private MessagesProps messages;
	@Autowired
	private ClientService clientService;

    // ---------------
    // Methods
    // ---------------
	@DeleteMapping (path = "/{identifier}", produces = "application/json")
	@ApiOperation(value = "Removes a client")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> deleteClient (@PathVariable String identifier) {
		try {
			// To delete/remove a client
			this.clientService.delete (identifier);
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (path = "/{identifier}", produces = "application/json")
	@ApiOperation(value = "Gets a client")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> getClient (@PathVariable String identifier) {
		try {
			// To get a client by its identifier
			ClientApiResponse client = this.clientService.get (identifier);
			
			return new ResponseEntity<>(client, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (produces = "application/json")
	@ApiOperation(value = "List of clients")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> list () {
		try {
			// To get a list of clients
			List<ClientApiResponse> data = this.clientService.list ();
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping (produces = "application/json")
	@ApiOperation(value = "Inserts a new client")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> newClient (@RequestBody ClientApi client) {
		try {
			// To save a new client
			this.clientService.save (client.getIdentifier(), 
					client.getName(), client.getSurname());
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping (path = "/{identifier}", produces = "application/json")
	@ApiOperation(value = "Updates a client")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful operation"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Server Error"),
	})
	public ResponseEntity<?> updateClient (@RequestBody ClientApi client, @PathVariable String identifier) {
		try {
			// To update a client
			this.clientService.update (identifier, client.getIdentifier(), 
					client.getName(), client.getSurname());
			
			return new ResponseEntity<>(buildResponse(messages.getSucessTransaction()), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(buildResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
