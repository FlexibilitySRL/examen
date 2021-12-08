package ar.com.plug.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.service.impl.PurchaseImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/purchase")
@Api(value = "purchase REST Endpoint")
public class PurchaseController {

	private static final Logger logger = LogManager.getLogger(PurchaseController.class);

	@Autowired
	PurchaseImpl purchaseImpl;

	@ApiOperation(value = "check purchase transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping("/checkpurchase")
	public ResponseEntity<List<Purchase>> consultarTransacciones() {
		logger.info("check purchase transactions successfully");
		List<Purchase> list_Purchase = purchaseImpl.consultar();

		return ResponseEntity.accepted().body(list_Purchase);
	}

	@ApiOperation(value = "approval purchase transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@PutMapping("/approvalpurchase")
	public ResponseEntity<Object> aprobacionCompras(@RequestBody Long idCompra) {
		purchaseImpl.aprobarCompra(idCompra);
		return new ResponseEntity<>("approval purchase transactions successfully", HttpStatus.CREATED);
	}

	@ApiOperation(value = "create purchase")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@PostMapping("/add")
	public ResponseEntity<Object> crear() {
		logger.info("purchase is create successfully");
		return new ResponseEntity<>("purchase is create successfully", HttpStatus.CREATED);
	}

}
