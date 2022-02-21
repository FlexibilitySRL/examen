package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerBydocumentNumber(String documentNumber);
}
