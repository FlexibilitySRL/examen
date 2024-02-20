package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.CartException;
import ar.com.plug.examen.domain.exception.CustomerException;
import ar.com.plug.examen.domain.exception.OrderException;
import ar.com.plug.examen.domain.model.Orders;

import java.util.List;

public interface OrderService {
    public Orders addOrder(Integer cid) throws CustomerException, CartException;

    public Orders approveOrder(Integer orderId) throws OrderException;

    public Orders updateOrder(Orders order) throws OrderException;

    public Orders viewOrder(Integer orderId) throws OrderException;

    public List<Orders> viewAllOrder() throws OrderException;

    public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException;
}
