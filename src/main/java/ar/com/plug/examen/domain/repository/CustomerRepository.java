package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * query that find a customer by documentNumber
     * @param documentNumber value to find in customer table
     * @return object of customer if exist in database.
     */
    Customer findCustomerBydocumentNumber(String documentNumber);
}
