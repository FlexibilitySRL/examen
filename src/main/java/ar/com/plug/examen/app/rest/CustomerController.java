package ar.com.plug.examen.app.rest;

 

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.CustomerDto;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	
	@Autowired
	private Mapper mapper;
    
    private final CustomerService customerService;

    @Autowired
    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
 
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create successfully."),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto dto)
    {
    	Customer customerNew = mapper.map(dto, Customer.class);

    	return new ResponseEntity<>(customerService.create(customerNew), HttpStatus.OK);
    }

    
    @GetMapping(path = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
            @ApiResponse(code = 404, message = "Not Found.")
    })
    public ResponseEntity<List<Customer>> getCustomersAll() {
    	
    	List<Customer> customers = customerService.getCustomers();
        
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
    
    
    
	@GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
            @ApiResponse(code = 404, message = "Not Found.")
    })
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
    	
    	Optional<Customer> customer = customerService.getCustomerById(id);

        if (!customer.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
    }
    

	
    @PutMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request updated successfully."),
    })
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("id") Long id,@RequestBody CustomerDto dto) {
    	
    	
    	Customer customerToUpdate= mapper.map(dto, Customer.class);
 

    	return new ResponseEntity<>(customerService.update(id,customerToUpdate), HttpStatus.OK);
 
     }
    
    
	
    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
    })
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable("id") Long id) {
    	
    	customerService.delete(id);
 
        return new ResponseEntity<Customer>( HttpStatus.OK);
    }
    
    
}
