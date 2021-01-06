package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT count(s) FROM Customer  s where s.email = :email")
    Integer countEmail(@Param("email") String email);

    @Query("SELECT s FROM Customer  s where s.id = :id and s.deleteCustomer = false")
    Optional<Customer> findCustomerById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Customer s Set s.deleteCustomer = true where s.id = :id and s.deleteCustomer = false")
    int deleteCustomer(@Param("id") Long id);
}
