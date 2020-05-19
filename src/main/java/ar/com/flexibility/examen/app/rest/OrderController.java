package ar.com.flexibility.examen.app.rest;


import ar.com.flexibility.examen.app.dto.OrderDTO;
import ar.com.flexibility.examen.service.impl.OrderServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @ApiOperation(value = "Crear nueva Orden")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
        return new ResponseEntity("Order saved successfully", HttpStatus.OK);
    }
    @ApiOperation(value = "Modificar Orden")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOrder(@PathVariable Long  id, @RequestBody OrderDTO orderDTO){
          orderService.updateOrder(id, orderDTO);
        return new ResponseEntity("Order updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "aprobar Orden")
    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    public ResponseEntity approveOrder(@PathVariable Long id){
        orderService.approveOrder(id);
        return new ResponseEntity("Order updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Cancelar Orden")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity cancelOrder(@PathVariable Long id){
        orderService.cancelOrderById(id);
        return new ResponseEntity("Order Cancelled successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Consultar Ordenes por cliente")
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDTO>> findOrdersByCustomer(@PathVariable Long customerId){
        List<OrderDTO> orders =  orderService.findOrdersByCustomer(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
