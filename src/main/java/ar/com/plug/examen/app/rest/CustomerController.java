package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;
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

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) throws Exception {
        return new ResponseEntity<Customer>(customerService.addCustomer(c), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) throws Exception {
        return new ResponseEntity<Customer>(customerService.updateCustomer(c), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Customer> removeCustomerById(@PathVariable("id") Integer customerId) throws Exception {
        return new ResponseEntity<Customer>(customerService.remove(customerId), HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Customer>> viewAllCustomer() throws Exception {
        return new ResponseEntity<List<Customer>>(customerService.viewAllCustomer(), HttpStatus.OK);
    }
}
