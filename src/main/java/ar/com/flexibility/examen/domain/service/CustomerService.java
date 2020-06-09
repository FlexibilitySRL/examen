package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;

import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);
    void delete(Long id);
    Optional<Product> findAll();
}
