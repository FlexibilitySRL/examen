package ar.com.flexibility.examen.model.repository;

import ar.com.flexibility.examen.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

}
