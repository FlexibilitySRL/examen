package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Order;

import java.util.List;

public interface OrderService {

    Order retrieveOrderById(Long id);
    List<Order> retrieveOrders();
}
