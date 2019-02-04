package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.model.Customer;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
