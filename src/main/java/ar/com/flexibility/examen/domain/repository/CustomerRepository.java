package ar.com.flexibility.examen.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public Customer findByName(String name);

	public Optional<Customer> findById(Integer id);
}
