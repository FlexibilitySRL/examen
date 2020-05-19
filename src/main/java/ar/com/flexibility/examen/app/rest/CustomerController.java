package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.dto.CustomerDTO;
import ar.com.flexibility.examen.service.impl.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @ApiOperation(value = "Alta de Nuevo Cliente")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.createCustomer(customerDTO);
        return new ResponseEntity("Customer saved successfully", HttpStatus.OK);
    }
    @ApiOperation(value = "Modificación de Cliente")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody  CustomerDTO customerDTO){
        customerService.updateCustomer(id, customerDTO);
        return new ResponseEntity("Customer updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Baja de Cliente")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
       customerService.deleteCustomerById(id);
        return new ResponseEntity("Customer deleted successfully", HttpStatus.OK);
    }
}
