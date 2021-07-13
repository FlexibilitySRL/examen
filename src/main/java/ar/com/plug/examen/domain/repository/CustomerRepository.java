package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.plug.examen.domain.model.Customer;


/**
 * @author Joan Rey
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.code = ?1")
    public Customer findByCode(String code);
}
