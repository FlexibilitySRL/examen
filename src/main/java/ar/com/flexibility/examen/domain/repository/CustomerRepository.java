package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository <Customer, Long>{

	Customer findByEmail(String email);
}
