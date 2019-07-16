package ar.com.flexibility.examen.app.rest;


import ar.com.flexibility.examen.app.exception.FlexibilityException;
import ar.com.flexibility.examen.app.exception.FlexibilityNotFoundException;
import ar.com.flexibility.examen.domain.service.CustomerService;
import ar.com.flexibility.examen.domain.service.dto.CustomerDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Customers API")
@RestController
@RequestMapping("/api")
public class CustomerController extends BaseController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * {@code POST  /customers} : Create a new customer.
     *
     * @param customerDTO the customerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerDTO, or with status {@code 400 (Bad Request)} if the customerDTO has already an ID.
     */
    @ApiOperation("Create a Customer")
    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> create(@ApiParam("A Customer") @Valid @RequestBody CustomerDTO customerDTO) {
        log.debug("REST request to create Customer : {}", customerDTO);
        if (!ObjectUtils.isEmpty(customerDTO.getId())) {
            throw new FlexibilityException("A new Customer cannot already have an ID");
        }
        return ResponseEntity.ok(this.customerService.save(customerDTO));
    }

    /**
     * {@code PUT  /customers} : Updates an existing customer.
     *
     * @param customerDTO the customerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerDTO,
     * or with status {@code 400 (Bad Request)} if the customerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerDTO couldn't be updated.
     */
    @ApiOperation(value = "Update a Customer")
    @PutMapping("/customers")
    public ResponseEntity<CustomerDTO> update(@ApiParam("A Customer") @Valid @RequestBody CustomerDTO customerDTO) {
        log.debug("REST request to update Customer : {}", customerDTO);

        if (ObjectUtils.isEmpty(customerDTO.getId())) {
            throw new FlexibilityException("ID cannot be null");
        }

        return ResponseEntity.ok(customerService.save(customerDTO));
    }

    /**
     * {@code GET  /customers} : get all the customers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @ApiOperation(value = "Get all Customers")
    @GetMapping("/customers")
    public List<CustomerDTO> findAll() {

        log.debug("REST request to get all Customers");
        return customerService.findAll();
    }

    /**
     * {@code GET  /customers/:id} : get the "id" customer.
     *
     * @param id the id of the customerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> findById(@ApiParam("Customer id") @PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);

        return customerService.findOne(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new FlexibilityNotFoundException("ID not found"));

    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" customer.
     *
     * @param id the id of the customerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @ApiOperation(value = "Remove a Customer")
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> delete(@ApiParam("Customer id") @PathVariable Long id) {
        log.debug("REST request to delete Contact : {}", id);
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
