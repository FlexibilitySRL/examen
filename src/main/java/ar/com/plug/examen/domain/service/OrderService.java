package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Order;

public interface OrderService {

	public List<Order> getOrderFiltering(String status,List<Long> productId);
	
	public List<Order> getAllOrders();
	
	public List<Order> getAllOrdersByStatus(String status);
	
	public List<Order> getAllOrdersByProductIds(List<Long> productIds);

	public Order getOrderById(Long id);

	public Order cancelOrder(Long id);

	public Order create(Order order);
	
	public Order approve(Long orderId,Long sellerId);
	
}
