package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customerDTO);
    CustomerDTO update(CustomerDTO customerDTO);
    void delete(String customerId);

    Optional<CustomerDTO> getById(String customerId);
    List<CustomerDTO> getAll();



}
