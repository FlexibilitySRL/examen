package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
    Customer findById(Long id);
}
