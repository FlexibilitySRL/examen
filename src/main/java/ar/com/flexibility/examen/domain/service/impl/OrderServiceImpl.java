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

/**
 *  Implementation of the OrderService that uses a CrudRepository. It
 *  connects to a Database defined in the application.yml file.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     *  Retrieves a Order model from the repository.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @param id Order ID
     * @return Order POJO or null if it does not exist.
     */
    @Override
    public Order retrieveOrderById(Long id) {
        logger.trace(String.format("Calling the retrieveOrderById(%s) method.", id));

        Order order = orderRepository.findOne(id);

        if (order == null) {
            logger.debug(String.format("Could not retrieve order with id %s", id));
        }

        return order;
    }

    /**
     *  Retrieves a list of Order models from the repository.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @return List of order models.
     */
    @Override
    public List<Order> retrieveOrders() {
        logger.trace("Calling the retrieveOrders method.");

        return orderRepository.findAll();
    }

    /**
     *  Retrieves a list of Order models filtered by seller from the repository.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @param seller Seller POJO
     * @return List of order models.
     */
    @Override
    public List<Order> retrieveOrderBySeller(Seller seller) {
        logger.trace(String.format("Calling the retrieveOrderBySeller(%s) method.", seller.toString()));

        return orderRepository.getOrdersBySeller(seller);
    }

    /**
     *  Updates the status of an order.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @param order Order POJO
     * @param status true if the order is approved, false if it was rejected.
     * @return True if it was updated, otherwise false.
     */
    @Override
    public boolean updateOrderStatus(Order order, boolean status) {
        logger.trace(String.format(
                "Calling the retrieveOrderBySeller(%s, %s) method.",
                order.toString(), status));

        try {
            order.setApproved(status);
            order.setApprovedAt(LocalDateTime.now());
        } catch (Exception e) {
            logger.warn(String.format(
                    "Could not set the status for order %s to %s. Exception: %s",
                    order.getId(),
                    status,
                    e.getMessage()));
            return false;
        }

        return true;
    }
}
