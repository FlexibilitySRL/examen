package ar.com.plug.examen.app.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/product")
@Api(value = "product REST Endpoint", description = "Shows REST Endpoint")
public class ProductosController {
	
	private static final Logger logger = LogManager.getLogger(ProductosController.class);
	
	@ApiOperation(value = "consulted product")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping("/list")
	public ResponseEntity<Object> consultar() {
		logger.info("Product is consulted successfully");
		return new ResponseEntity<>("Product is consulted successfully", HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "create product")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@PostMapping("/add")
	public ResponseEntity<Object> crear() {
		logger.info("Product is create successfully");
		return new ResponseEntity<>("Product is create successfully", HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "edit product")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@PutMapping("/edit")
	public ResponseEntity<Object> editar() {
		logger.info("Product is edit successfully");
		return new ResponseEntity<>("Product is edit successfully", HttpStatus.CREATED);
	}
	
	
	

}
