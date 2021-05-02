package ar.com.plug.examen.datasource.repo;

import ar.com.plug.examen.datasource.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    public List<Customer> findAllByName(String name);

}