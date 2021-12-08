package ar.com.plug.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.impl.CustomerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/customer")
@Api(value = "customer REST Endpoint")
public class CustomerController {
	
	@Autowired
	private CustomerImpl customerImpl;
	
private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	@ApiOperation(value = "customer shopping")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping("/list")
	public ResponseEntity<List<Customer>> consultar() {
		logger.info("customer is consulted successfully");
		
		List<Customer> list_Customer= customerImpl.consultar();
		
		return ResponseEntity.accepted().body(list_Customer);
	}
	
	@ApiOperation(value = "customer shopping")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@PostMapping("/add")
	public ResponseEntity<Object> crear() {
		logger.info("customer is create successfully");
		return new ResponseEntity<>("customer is create successfully", HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "edit customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@PutMapping("/edit")
	public ResponseEntity<Object> editar() {
		logger.info("customer is edit successfully");
		return new ResponseEntity<>("customer is edit successfully", HttpStatus.CREATED);
	}

}
