package ar.com.flexibility.examen.domain.repo;

import ar.com.flexibility.examen.domain.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    public List<Customer> findByMaskname(String maskname);
}
