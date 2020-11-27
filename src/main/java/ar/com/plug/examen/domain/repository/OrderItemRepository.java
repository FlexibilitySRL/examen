package ar.com.plug.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long > {

}
