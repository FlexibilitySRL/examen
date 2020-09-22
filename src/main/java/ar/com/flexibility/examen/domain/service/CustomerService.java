package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.dto.CustomerDTO;

public interface CustomerService {

    public CustomerDTO createCustomer(CustomerDTO customer);
    
    public CustomerDTO updateCustomer(CustomerDTO customer);
    
    public CustomerDTO deleteCustomer(Long id);
    
    public CustomerDTO getCustomer(Long id);
}
