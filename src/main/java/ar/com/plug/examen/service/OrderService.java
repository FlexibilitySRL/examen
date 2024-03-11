package ar.com.plug.examen.service;

import ar.com.plug.examen.domain.model.OrderShopping;

import java.util.List;

public interface OrderService {

    OrderShopping createOrder(OrderShopping order);

    OrderShopping getOrderById(Long id);

    List<OrderShopping> getAllOrders();

    OrderShopping updateOrder(OrderShopping order);

    void deleteOrder(Long id);
}

