package ar.com.flexibility.examen.model.repository;

import ar.com.flexibility.examen.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
