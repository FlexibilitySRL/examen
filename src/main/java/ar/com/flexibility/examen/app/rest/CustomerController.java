package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/customer")
public class CustomerController {
    
    @Autowired
    CustomerService service;
    
    @GetMapping(path = "/{customerId}")
    public ResponseEntity<Customer> get(@PathVariable Long customerId) {
        Customer customer = service.read(customerId);
        if (customer == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
    
    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<?> delete(@PathVariable Long customerId) {
        service.delete(customerId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    
    @PutMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer created = service.create(customer);
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> modify(@RequestBody Customer customer) {
        Customer updated = service.update(customer);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(customer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
