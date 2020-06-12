package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Customer;
import java.util.List;

public interface CustomerService {
    public Customer create(Customer customer);
    public Customer read(Long id);
    public Customer update(Customer customer);
    public void delete(Long id);
    public List<Customer> search(String maskname);
}
