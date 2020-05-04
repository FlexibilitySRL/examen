package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
