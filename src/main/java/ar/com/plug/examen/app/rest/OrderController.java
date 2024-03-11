package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.OrderShopping;
import ar.com.plug.examen.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderShopping> createOrder(@RequestBody OrderShopping order) {
        OrderShopping savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderShopping> getOrderById(@PathVariable Long id) {
        OrderShopping order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderShopping>> getAllOrders() {
        List<OrderShopping> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderShopping> updateOrder(@PathVariable Long id, @RequestBody OrderShopping order) {
        order.setId(id); // Ensure ID matches path variable
        OrderShopping updatedOrder = orderService.updateOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

