package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/api/v1/customers")
    public Page<Customer> getCustomers(@PageableDefault(page = 0, size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort = "firstName", direction = Sort.Direction.DESC) }) Pageable pageable) {

        return customerService.findAll(pageable);
    }

    @GetMapping("/api/v1/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") long customerId) {

        Customer oCustomer = customerService.getById(customerId);
        if (oCustomer != null)
            return new ResponseEntity<>(oCustomer, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/v1/customers")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Customer result = customerService.save(customer);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/api/v1/customers")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Customer result = customerService.update(customer);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
