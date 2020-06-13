package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/customer")
public class CustomerController {
    
    @Autowired
    CustomerService service;
    
    @GetMapping(path = "/{customerId}")
    public Customer get(@PathVariable Long customerId) throws NotFoundException {
        Customer customer = service.read(customerId);
        if (customer == null) {
            throw new NotFoundException();
        }
        
        return customer;
    }
    
    @DeleteMapping(path = "/{customerId}")
    public void delete(@PathVariable Long customerId) throws NotFoundException {
        try{
            service.delete(customerId);
        }
        catch (Exception e) {
            throw new NotFoundException();
        }
    }
    
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer) throws BadRequestException {
        Customer created = service.create(customer);
        if (created == null) {
            throw new BadRequestException();
        }
        return created;
    }

    @PostMapping
    public Customer modify(@RequestBody Customer customer) throws BadRequestException {
        Customer updated = service.update(customer);
        if (updated == null) {
            throw new BadRequestException();
        }
        return updated;
    }
}
