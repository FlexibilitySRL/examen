package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.service.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDTO> findOne(Long id);

    List<CustomerDTO> findAll();

    CustomerDTO save(CustomerDTO customerDTO);

    void delete(Long id);

}
