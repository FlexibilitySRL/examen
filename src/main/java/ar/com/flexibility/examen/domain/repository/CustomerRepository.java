package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
   
	public Customer findByDocument(String document);

}