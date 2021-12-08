package ar.com.plug.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
