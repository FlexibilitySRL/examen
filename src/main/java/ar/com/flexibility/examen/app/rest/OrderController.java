package ar.com.flexibility.examen.app.rest;

import java.security.Principal;
import javax.validation.Valid;

import ar.com.flexibility.examen.app.api.dto.OrderDTO;
import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createOrder(Principal principal, @Valid @RequestBody OrderDTO orderDTO) {
		Order createdOrder = orderService.createOrder(principal, orderDTO);
		return new ResponseEntity<>(createdOrder, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Order>> getOrders(Pageable pageable) {
		Page<Order> orders = orderService.getOrders(pageable);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

}
