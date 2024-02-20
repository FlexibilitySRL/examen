package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c.orders from Customer c where c.cId=?1")
    public List<Orders> getAllOrderByCid(Integer cId);

    public Customer findByEmail(String email);
}
