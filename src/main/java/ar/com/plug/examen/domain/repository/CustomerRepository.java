package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Customer entities.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
