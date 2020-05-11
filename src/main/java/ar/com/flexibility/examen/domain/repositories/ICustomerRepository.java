package ar.com.flexibility.examen.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
	
}
