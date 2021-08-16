package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import static ar.com.plug.examen.utils.QueryUtils.QUERY_LAST_ID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
    Customer findById(Long id);
}
