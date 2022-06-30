package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.app.rest.responses.ChallengeResponse;
import ar.com.plug.examen.domain.execptions.ChallengeException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private static final String STATUS_SUCCESS = "Success";

    @Autowired
    CustomerService customerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{customerId}")
    public ChallengeResponse<CustomerApi> getCustomerById(@PathVariable Long customerId) throws ChallengeException {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", customerService.getCustomerById(customerId));
    }

    @PostMapping()
    public ChallengeResponse<CustomerApi> createCustomer(@RequestBody CustomerApi customerApi) {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.CREATED), "OK", customerService.createCustomer(customerApi));
    }

    @GetMapping()
    public ChallengeResponse<List<CustomerApi>> listAllCustomers() {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", customerService.listAllCustomers());
    }

    @DeleteMapping("/{customerId}")
    public ChallengeResponse removeCustomer(@PathVariable Long customerId) throws NotFoundException {
        customerService.removeCustomer(customerId);
        return new ChallengeResponse<CustomerApi>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK");
    }

    @PutMapping(value = "/{customerId}")
    public ChallengeResponse<CustomerApi> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerApi customerApi) throws NotFoundException {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", customerService.updateCustomer(customerId, customerApi));
    }
}
