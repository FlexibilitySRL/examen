package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/saveCustomer",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void createCustomer(@RequestBody CustomerDTO customerDTO) {
        System.out.println("controller" + customerDTO.getDocumentNumber());
        customerService.createCustomer(customerDTO);
    }

    @DeleteMapping(path = "/removeCustomer/{idCustomer}",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@PathVariable("idCustomer") Long id) {
        customerService.deleteCustomer(id);
    }

    @PostMapping(path = "/editCustomer/{idCustomer}",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void modifyCustomer(@PathVariable("idCustomer") Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.editCustomer(id, customerDTO);
    }

}
