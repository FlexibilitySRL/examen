package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Customer;
import ar.com.plug.examen.domain.service.ProcessCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private final ProcessCustomerService processCustomerService;

    @Autowired
    public CustomerController(ProcessCustomerService processCustomerService) {
        this.processCustomerService = processCustomerService;
    }

    @PostMapping(path = "create", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> echo(@RequestBody Customer customer) {
        return new ResponseEntity<>(processCustomerService.save(customer), HttpStatus.OK);
    }

}
