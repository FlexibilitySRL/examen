package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.CartException;
import ar.com.plug.examen.domain.exception.CustomerException;
import ar.com.plug.examen.domain.exception.OrderException;
import ar.com.plug.examen.domain.model.Cart;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Orders;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.OrderRepository;
import ar.com.plug.examen.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Orders addOrder(Integer cid) throws CustomerException, CartException {

        Optional<Customer> opt = customerRepository.findById(cid);
        if (opt.isEmpty()) {
            throw new CustomerException("Customer not found");
        }

        Customer c = opt.get();
        Cart cart = c.getCart();
        Orders o = new Orders();

        o.setDate(LocalDateTime.now());
        o.setOrderStatus("Pending");
        o.setCustomer(c);
        if (cart.getProducts().isEmpty()) {
            throw new CartException("add minimum one product to order!");
        } else {
            o.setProductList(new ArrayList<>(cart.getProducts()));
            return orderRepository.save(o);
        }

    }

    @Override
    public Orders approveOrder(Integer orderId) throws OrderException {

        Optional<Orders> opt = orderRepository.findById(orderId);
        if (opt.isEmpty()) {
            throw new OrderException("Order not found");
        }

        Orders order = opt.get();
        order.setOrderStatus("Completed");
        return orderRepository.save(order);
    }

    @Override
    public Orders updateOrder(Orders order) throws OrderException {
        Orders o = orderRepository.findById(order.getOrderId()).orElseThrow(() -> new OrderException("Order not found"));
        if (o != null) {
            orderRepository.save(order);
        }
        return o;
    }

    @Override
    public Orders viewOrder(Integer orderId) throws OrderException {
        Optional<Orders> o = orderRepository.findById(orderId);
        if (o.isPresent()) {
            return o.get();
        } else {
            throw new OrderException("order not present!!");
        }

    }

    @Override
    public List<Orders> viewAllOrder() throws OrderException {
        List<Orders> orders = orderRepository.findAll();
        if (orders.size() > 0) {
            return orders;
        } else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public List<Orders> viewAllOrdersByUserId(Integer uderId) throws OrderException {
        List<Orders> orders = customerRepository.getAllOrderByCid(uderId);
        if (orders.size() > 0) {
            return orders;
        } else {
            throw new OrderException("Order not found");
        }
    }
}
