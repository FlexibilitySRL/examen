package ar.com.plug.examen.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.OrderService;
import ar.com.plug.examen.enums.OrderStatus;
import ar.com.plug.examen.exception.NotDataFoundException;
import ar.com.plug.examen.exception.NotOrderFoundException;
import ar.com.plug.examen.exception.NotSellerFoundException;
import ar.com.plug.examen.repository.OrderRepository;
import ar.com.plug.examen.repository.SellerRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public List<Order> getAllOrders() {
		return (List<Order>) repository.findAll();
	}

	@Override
	public List<Order> getAllOrdersByStatus(String status) {
		return repository.findByStatus(status.toUpperCase());
	}

	@Override
	public Order getOrderById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotDataFoundException(id));
	}

	@Override
	public Order cancelOrder(Long id) {
		Order order = repository.findById(id).orElseThrow(() -> new NotDataFoundException(id));
		order.setStatus(OrderStatus.CANCELED.name());
		repository.save(order);
		return order;
	}

	@Override
	public Order create(Order order) {
		order.setStatus(OrderStatus.PENDING.name());
		return repository.save(order);
	}

	@Override
	public Order approve(Long orderId, Long sellerId) {
		Order order = repository.findById(orderId).orElseThrow(() -> new NotOrderFoundException(orderId));
		Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new NotSellerFoundException(sellerId));
		order.setModificationDate(new Date());
		order.setStatus(OrderStatus.APPROVED.name());
		order.setSeller(seller);
		order.setTransactionId(UUID.randomUUID().toString());
		return repository.save(order);
	}

}
