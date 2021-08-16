package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

import static ar.com.plug.examen.utils.QueryUtils.QUERY_LAST_ID;

public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
    @Query(value=QUERY_LAST_ID, nativeQuery = true)
    Integer findTopByOrderByIdDesc();
    Customer findById(Integer id);
    Customer findByName(String name);
}
