package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.OrderApi;
import ar.com.plug.examen.app.api.OrderToApproveApi;
import ar.com.plug.examen.app.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void placeOrder(OrderApi orderApi);

    List<OrderDto> findAll();

    boolean confirmOrder(OrderToApproveApi orderApi);
}
