package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.repository.OrderRepository;
import ar.com.flexibility.examen.domain.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
