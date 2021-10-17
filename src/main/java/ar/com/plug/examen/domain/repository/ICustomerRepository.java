package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {

    Customer save(Customer customer);
    Customer update(Customer customer);
    void delete(String customerId);

    Optional<Customer> getById(String customerId);
    List<Customer> getAll();

}
