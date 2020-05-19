package ar.com.flexibility.examen.service;

import ar.com.flexibility.examen.app.dto.OrderDTO;
import ar.com.flexibility.examen.model.Order;

import java.util.List;

public interface OrderService {

    void createOrder(OrderDTO orderDTO);
    Order retrieveOrderById(Long id);
    void updateOrder(Long id, OrderDTO orderDTO);
    void cancelOrderById(Long id);
    List<OrderDTO> findOrdersByCustomer(Long customerId);
    void approveOrder(Long id);
}
