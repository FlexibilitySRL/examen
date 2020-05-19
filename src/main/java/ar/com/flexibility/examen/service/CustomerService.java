package ar.com.flexibility.examen.service;

import ar.com.flexibility.examen.app.dto.CustomerDTO;
import ar.com.flexibility.examen.model.Customer;

public interface CustomerService {

    void createCustomer(CustomerDTO customer);
    Customer retrieveCustomerById(Long id);
    void updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
}
