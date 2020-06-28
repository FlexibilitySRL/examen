package ar.com.flexibility.examen.app.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.CustomerService;

@Api(tags = "Customer API")
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

	private final Logger log = LoggerFactory.getLogger(CustomController.class);
	
	@Autowired
	CustomerService customerService;
	
	/**
     * {@code POST  /create} : Create a new customer.
     *
     * @param Customer the customer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customer,
     * or with status {@code 400 (Bad Request)} if the customer has already an ID.
     */
    @ApiOperation(value="Create a Customer",notes="Service used to create a customer given its name, last name, email, cellPhoness, address")
    @PostMapping("/create")
    public ResponseEntity<Customer> create(@ApiParam("A Customer") @Valid @RequestBody Customer customer) {
        log.debug("REST request to create Customer : {}", customer);
        if (!ObjectUtils.isEmpty(customer.getId())) {
            throw new RuntimeException("A new Customer cannot already have an ID");
        }
        return ResponseEntity.ok(this.customerService.save(customer));
    }

    /**
     * {@code PUT  /update} : Updates an existing customer.
     *
     * @param Customer the Customer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Customer,
     * or with status {@code 400 (Bad Request)} if the Customer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Customer couldn't be updated.
     */
    @ApiOperation(value = "Update a Customer",notes="Service used to update a customer")
    @PutMapping("/update")
    public ResponseEntity<Customer> update(@ApiParam("A Customer") @Valid @RequestBody Customer Customer) {
        log.debug("REST request to update Customer : {}", Customer);

        if (ObjectUtils.isEmpty(Customer.getId())) {
            throw new RuntimeException("ID cannot be null");
        }

        return ResponseEntity.ok(customerService.save(Customer));
    }

    /**
     * {@code GET  /} : get all the customers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @ApiOperation(value = "Get all Customers")
    @GetMapping("/")
    public List<Customer> findAll() {

        log.debug("REST request to get all Customers");
        return customerService.getAllCustomers();
    }

    /**
     * {@code GET /:id} : get the "id" customer.
     *
     * @param id the id of the Customer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Customer, or with status
     * {@code 404 (Not Found)}.
     */
    @ApiOperation(value="Get a Customer",notes="Service used to get a customer")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@ApiParam(value = "Customer id", required = true) @PathVariable Long id) {
        
    	log.debug("REST request to get Customer : {}", id);

    	Customer customer = customerService.getCustomerById(id);
    	
        return ResponseEntity.ok(customer);

    }
    
    /**
     * {@code GET /getByEmail/:email} : get the "id" customer.
     *
     * @param id the id of the Customer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Customer, or with status
     * {@code 404 (Not Found)}.
     */
    @ApiOperation(value="Get a Customer",notes="Service used to get a customer")
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Customer> findCustomerByEmail(@ApiParam(value = "Customer email", required = true) @PathVariable String email) {
        
    	log.debug("REST request to get Customer : {}", email);

    	Customer customer = customerService.getCustomerByEmail(email);
    	
        return ResponseEntity.ok(customer);

    }

    /**
     * {@code DELETE  /delete/:id} : delete the "id" customer.
     *
     * @param id the id of the Customer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @ApiOperation(value = "Remove a Customer",notes="Service used to delete a customer")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@ApiParam(value = "Customer id", required = true) @PathVariable Long id) {
        log.debug("REST request to delete Contact : {}", id);
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
