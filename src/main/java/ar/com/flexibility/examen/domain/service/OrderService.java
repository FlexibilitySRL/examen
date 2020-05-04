package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Seller;

import java.util.List;

/**
 *  Order Service contract. It exposes the methods that should be
 *  implemented by the more specific classes.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
public interface OrderService {

    Order retrieveOrderById(Long id);
    List<Order> retrieveOrders();
    List<Order> retrieveOrderBySeller(Seller seller);

    boolean updateOrderStatus(Order order, boolean status);
}
