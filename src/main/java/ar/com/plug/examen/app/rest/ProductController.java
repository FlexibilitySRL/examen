package ar.com.plug.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/product")
@Api(value = "product REST Endpoint")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@Autowired
	ProductImpl productImpl;

	@ApiOperation(value = "consulted product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping("/list")
	public ResponseEntity<List<Product>> consultar() {
		logger.info("Product is consulted successfully");
		List<Product> list_Product = productImpl.consultar();

		return ResponseEntity.accepted().body(list_Product);
	}

	@ApiOperation(value = "create product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@PostMapping("/add")
	public ResponseEntity<Object> crear() {
		logger.info("Product is create successfully");
		return new ResponseEntity<>("Product is create successfully", HttpStatus.CREATED);
	}

	@ApiOperation(value = "edit product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@PutMapping("/edit")
	public ResponseEntity<Object> editar() {
		logger.info("Product is edit successfully");
		return new ResponseEntity<>("Product is edit successfully", HttpStatus.CREATED);
	}

}
