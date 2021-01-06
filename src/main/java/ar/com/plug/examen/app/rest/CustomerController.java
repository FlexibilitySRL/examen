package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.domain.service.CustomerUseCase;
import ar.com.plug.examen.infrastructure.exception.CustomerEmailExistException;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerUseCase service;

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@Valid @RequestBody final CustomerApi customer)
            throws CustomerEmailExistException {
        return new ResponseEntity<>(service.save(customer), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody final CustomerApi customer,  @PathVariable final Long id)
            throws CustomerEmailExistException ,ResourceNotFoundException {
        return new ResponseEntity<>(service.update(customer,id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCustomer(@PathVariable final Long id)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(service.delete(id), HttpStatus.ACCEPTED);
    }

}
