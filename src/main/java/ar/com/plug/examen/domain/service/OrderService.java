package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Order;

public interface OrderService {

	Order create(Order order);
	Order update(Long id, Order order);
	void delete(Long id);
	List<Order> getOrders();
	Optional<Order> getOrderById(Long id);
}
