package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.OrderRepository;
import ar.com.flexibility.examen.domain.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order retrieveOrderById(Long id) {
        Order order = orderRepository.findOne(id);

        if (order == null) {
            logger.trace(String.format("Could not retrieve order with id %s", id));
        }

        return order;
    }

    @Override
    public List<Order> retrieveOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> retrieveOrderBySeller(Seller seller) {
        return orderRepository.getOrdersBySeller(seller);
    }

    @Override
    public boolean updateOrderStatus(Order order, boolean status) {
        try {
            order.setApproved(status);
            order.setApprovedAt(LocalDateTime.now());
        } catch (Exception e) {
            logger.trace(String.format(
                    "Could not set the status for order %s to %s. Exception: %s",
                    order.getId(),
                    status,
                    e.getMessage()));
            return false;
        }

        return true;
    }
}
