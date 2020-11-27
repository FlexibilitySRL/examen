package ar.com.plug.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long > {

}
