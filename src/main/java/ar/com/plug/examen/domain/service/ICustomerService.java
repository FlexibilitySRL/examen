package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface ICustomerService {
    Page<Customer> findAll(Pageable pageable);
    Customer save(@Valid Customer customer);
    Customer getById(Long id);
    Customer update(@Valid Customer customer);
}
