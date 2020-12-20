package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
