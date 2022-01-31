package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.api.OrderDTO;
import ar.com.plug.examen.app.api.OrderRequest;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.OrderService;
import ar.com.plug.examen.domain.validators.Validator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        validator.validateTransaction(orderRequest);
        return new ResponseEntity<>(orderService.save(orderRequest), HttpStatus.CREATED);
    }


}
