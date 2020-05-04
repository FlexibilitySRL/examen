package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o from orders o WHERE o.client = :client")
    List<Order> getOrdersByClient(@Param("client") Client client);

    @Query("SELECT o from orders o WHERE o.seller = :seller")
    List<Order> getOrdersBySeller(@Param("seller")Seller seller);
}
