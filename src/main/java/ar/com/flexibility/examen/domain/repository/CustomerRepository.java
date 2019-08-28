package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	Customer findByEmail(String email);

}
