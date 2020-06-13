package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/customer")
public class CustomerController {
    
    @Autowired
    CustomerService service;
    
    @RequestMapping(path = "/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<Customer> get(@PathVariable Long customerId) {
        Customer customer = service.read(customerId);
        if (customer == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
    
    @RequestMapping(path = "/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long customerId) {
        service.delete(customerId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer created = service.create(customer);
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
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
