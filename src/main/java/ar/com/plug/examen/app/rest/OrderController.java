package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.exception.CartException;
import ar.com.plug.examen.domain.exception.CustomerException;
import ar.com.plug.examen.domain.exception.OrderException;
import ar.com.plug.examen.domain.model.Orders;
import ar.com.plug.examen.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<Orders> addOrder(@RequestParam("customerId") Integer customerId)
            throws CustomerException, CartException {
        return new ResponseEntity<Orders>(orderService.addOrder(customerId), HttpStatus.CREATED);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<Orders> approveOrder(@PathVariable("id") Integer orderId) throws OrderException {
        return new ResponseEntity<Orders>(orderService.approveOrder(orderId), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders order) throws OrderException {
        return new ResponseEntity<Orders>(orderService.updateOrder(order), HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Orders> viewOrderById(@PathVariable("id") Integer orderId) throws OrderException {
        return new ResponseEntity<Orders>(orderService.viewOrder(orderId), HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Orders>> viewAllOrder() throws OrderException {
        return new ResponseEntity<List<Orders>>(orderService.viewAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/view/{userId}")
    public ResponseEntity<List<Orders>> viewOrderByUserId(@PathVariable("userId") Integer userId)
            throws OrderException {
        return new ResponseEntity<List<Orders>>(orderService.viewAllOrdersByUserId(userId), HttpStatus.OK);
    }
}
