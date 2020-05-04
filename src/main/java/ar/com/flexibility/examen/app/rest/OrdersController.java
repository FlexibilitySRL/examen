package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.service.OrderService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  The ClientController class exposes the query operations for the orders models.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/orders")
public class OrdersController {

    private OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully.")
    })
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.retrieveOrders();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 404, message = "Could not retrieve the resource.")
    })
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.retrieveOrderById(id);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
