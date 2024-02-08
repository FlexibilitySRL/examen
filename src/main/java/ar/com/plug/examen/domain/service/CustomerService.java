package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.app.DTO.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing Customer operations.
 */
public interface CustomerService {

    /**
     * Adds a new customer.
     *
     * @param customerDTO The customer details.
     */
    CustomerDTO addCustomer(CustomerDTO customerDTO);

    /**
     * Updates an existing customer.
     *
     * @param customerId  The unique identifier of the customer.
     * @param customerDTO The updated customer details.
     */
    CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDTO);

    /**
     * Deletes a customer.
     *
     * @param customerId The unique identifier of the customer.
     */
    void deleteCustomer(UUID customerId);

    /**
     * Retrieves a list of all customers.
     *
     * @return List of all customers.
     */
    List<CustomerDTO> getAllCustomers();

    /**
     * Retrieves a customer by its unique identifier.
     *
     * @param customerId The unique identifier of the customer.
     * @return Optional containing the customer details, or empty if not found.
     */
    Optional<CustomerDTO> getCustomerById(UUID customerId);
}
