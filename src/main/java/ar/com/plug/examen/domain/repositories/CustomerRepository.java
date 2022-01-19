package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}