package ar.com.flexibility.examen.app.service;

import java.security.Principal;

import ar.com.flexibility.examen.app.api.dto.OrderDTO;
import ar.com.flexibility.examen.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

	Order createOrder(Principal principal, OrderDTO orderDTO);

	Page<Order> getOrders(Pageable pageable);

}
