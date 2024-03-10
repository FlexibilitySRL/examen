package ar.com.plug.examen.service;

import ar.com.plug.examen.domain.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    Order updateOrder(Order order);

    void deleteOrder(Long id);
}

