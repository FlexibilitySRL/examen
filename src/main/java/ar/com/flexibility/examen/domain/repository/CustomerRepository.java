package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}