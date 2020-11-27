package ar.com.plug.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long > {

}
