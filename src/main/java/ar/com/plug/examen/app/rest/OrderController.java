package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.OrderDTO;
import ar.com.plug.examen.app.api.OrderRequest;
import ar.com.plug.examen.domain.OrderStatusEnum;
import ar.com.plug.examen.domain.service.OrderService;
import ar.com.plug.examen.domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/orders")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private Validator validator;

    @PostMapping()
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderRequest orderRequest)
    {
        validator.validateOrder(orderRequest);
        return new ResponseEntity<>(orderService.save(orderRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrderItems() {
        return new ResponseEntity<>(orderService.getAllOrderItems(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/{status}")
    public ResponseEntity<OrderDTO> approveTransaction(@PathVariable Long id, @PathVariable String status)
    {
        validator.validateStatusApproved(status);
        return new ResponseEntity<>(orderService.updateStatus(id, OrderStatusEnum.valueOf(status)), HttpStatus.OK);
    }


}
