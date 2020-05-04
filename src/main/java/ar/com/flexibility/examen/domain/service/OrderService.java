package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Seller;

import java.util.List;

public interface OrderService {

    Order retrieveOrderById(Long id);
    List<Order> retrieveOrders();
    List<Order> retrieveOrderBySeller(Seller seller);

    boolean updateOrderStatus(Order order, boolean status);
}
