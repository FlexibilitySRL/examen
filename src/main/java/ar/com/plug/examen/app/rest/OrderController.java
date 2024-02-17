package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.OrderApi;
import ar.com.plug.examen.app.api.OrderToApproveApi;
import ar.com.plug.examen.app.dto.OrderDto;
import ar.com.plug.examen.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderApi orderApi) {
        orderService.placeOrder(orderApi);
        log.info("OrderController :: placeOrder :: Order placed successfully");
        return "Order placed successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> findAll() {
        List<OrderDto> orders = orderService.findAll();
        log.info("OrderController :: findAll :: Founded {}", orders.size());
        return orders;
    }

    @PostMapping("/approved")
    public ResponseEntity<String> confirmOrder(@RequestBody OrderToApproveApi orderApi) {
        boolean confirmed = orderService.confirmOrder(orderApi);
        if (!confirmed) {
            log.info("OrderController :: confirmOrder :: Order {} not found", orderApi.getOrderNumber());
            return ResponseEntity.notFound().build();
        }
        log.info("OrderController :: confirmOrder :: Order {} confirmed successfully", orderApi.getOrderNumber());
        return ResponseEntity.ok("Order confirmed successfully");
    }
}
