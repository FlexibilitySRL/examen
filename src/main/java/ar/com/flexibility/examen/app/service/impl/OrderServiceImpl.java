package ar.com.flexibility.examen.app.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import ar.com.flexibility.examen.app.api.dto.OrderDTO;
import ar.com.flexibility.examen.app.api.dto.OrderItemDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.OrderItem;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.OrderRepository;
import ar.com.flexibility.examen.app.service.CustomerService;
import ar.com.flexibility.examen.app.service.OrderService;
import ar.com.flexibility.examen.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	ProductService productService;

	@Autowired
	CustomerService customerService;

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order createOrder(Principal principal, OrderDTO orderDTO) {
		Order order = new Order();
		List<OrderItem> orderItems = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;

		for(OrderItemDTO orderItemDTO: orderDTO.getOrderItems()) {
			Product product = productService.getProductById(orderItemDTO.getProductId());
			total = total.add(product.getPrice().multiply(BigDecimal.valueOf(orderItemDTO.getQuantity())));
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setQuantity(orderItemDTO.getQuantity());
			orderItems.add(orderItem);
		}

		Customer customer = customerService.getCustomerByEmail(principal.getName());
		order.setCustomer(customer);
		order.setTotal(total);
		order.setOrderItems(orderItems);

		return orderRepository.save(order);

	}

	@Override
	public Page<Order> getOrders(Pageable pageable) {
		return orderRepository.findAll(new PageRequest(pageable.getPageNumber(), pageable.getPageSize()));
	}

}
