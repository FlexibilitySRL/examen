package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Customer}
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
