package ar.com.plug.examen.service.impl;

import ar.com.plug.examen.domain.model.OrderShopping;
import ar.com.plug.examen.repository.OrderRepository;
import ar.com.plug.examen.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderShopping createOrder(OrderShopping order) {
        order.setOrderDate(LocalDateTime.now()); // Set order creation date
        calculateTotalPrice(order); // Calculate total price before saving
        return orderRepository.save(order);
    }

    @Override
    public OrderShopping getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderShopping> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderShopping updateOrder(OrderShopping order) {
        calculateTotalPrice(order); // Recalculate total price before update
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private void calculateTotalPrice(OrderShopping order) {
        order.setTotal(order.calculateTotalPrice());
    }
}

