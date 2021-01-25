package ar.com.plug.examen.app.rest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping(path = "/orders", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllOrdersByStatus(@RequestParam(value = "status", required = false) String status) {
		List<Order> orders;
		if (StringUtils.hasText(status))
			orders = service.getAllOrdersByStatus(status);
		else
			orders = service.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping(path = "/orders/{orderid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getOrderById(@PathVariable Long orderid) {
		return new ResponseEntity<>(service.getOrderById(orderid), HttpStatus.OK);
	}

	@DeleteMapping(path = "/orders/{orderid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> cancelOrder(@PathVariable Long orderid) {
		return new ResponseEntity<>(service.cancelOrder(orderid), HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/orders", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@Valid @RequestBody Order order) {
		return new ResponseEntity<>(service.create(order), HttpStatus.CREATED);
	}

	@PutMapping(path = "/orders/{orderid}/sellers/{sellerid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> approve(@PathVariable Long orderid, @PathVariable Long sellerid) {
		return new ResponseEntity<>(service.approve(orderid, sellerid), HttpStatus.OK);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> "field " + x.getField() + " " + x.getDefaultMessage()).collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
