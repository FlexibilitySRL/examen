package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("save")
    @ApiOperation("Registrar un nuevo cliente")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDto){
        return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("update")
    @ApiOperation("Actualizar un cliente")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDto){
        return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ApiOperation("Buscar un cliente por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),

    })
    public ResponseEntity<CustomerDTO>  findById(
            @ApiParam(value = "Id del cliente  buscar", required = true, example = "1129578649")
            @PathVariable("id") String customerId){

        return customerService.getById(customerId)
                .map(customerDto -> new ResponseEntity<>(customerDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @ApiOperation("Consutar a todos los clientes")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<CustomerDTO>> findAll(){

        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

}
