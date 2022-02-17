package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.CustomerDTO;

public interface CustomerService {
    void createCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long idCustomer);

    void editCustomer(Long idCustomer, CustomerDTO customerDTO);
}
