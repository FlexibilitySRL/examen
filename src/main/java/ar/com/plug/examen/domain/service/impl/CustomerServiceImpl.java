package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.DTO.CustomerDTO;
import ar.com.plug.examen.app.mapper.CustomerMapper;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the CustomerService interface.
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    /**
     * Adds a new customer.
     *
     * @param customerDTO The customer data to be added.
     */
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        log.info("Adding customer: {}", customerDTO.name());
        Customer customer = new Customer(customerDTO.name());
        return mapper.asDTO(customerRepository.save(customer));
    }

    /**
     * Updates an existing customer with the given ID.
     *
     * @param customerId  The unique identifier of the customer to be updated.
     * @param customerDTO The new data for the customer.
     */
    @Override
    public CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        log.info("Updating customer with ID {}: {}", customerId, customerDTO.name());

        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        Customer updatedCustomer = new Customer(existingCustomer.getId(), customerDTO.name());

        return mapper.asDTO(customerRepository.save(updatedCustomer));
    }

    /**
     * Deletes an existing customer with the given ID.
     *
     * @param customerId The unique identifier of the customer to be deleted.
     */
    @Override
    public void deleteCustomer(UUID customerId) {
        log.info("Deleting customer with ID: {}", customerId);
        customerRepository.deleteById(customerId);
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return List of CustomerDTO representing all customers.
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {
        log.info("Fetching all customers");
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param customerId The unique identifier of the customer.
     * @return Optional containing CustomerDTO if found, empty otherwise.
     */
    @Override
    public Optional<CustomerDTO> getCustomerById(UUID customerId) {
        log.info("Fetching customer by ID: {}", customerId);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        return customerOptional.map(mapper::asDTO);
    }
}
