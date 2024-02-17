package ar.com.plug.examen.domain.mapper;

import ar.com.plug.examen.app.api.OrderItemApi;
import ar.com.plug.examen.app.dto.OrderDto;
import ar.com.plug.examen.app.dto.OrderItemDto;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderItem toOrderItem(OrderItemApi orderItemApi, Order order) {
        return OrderItem.builder()
                .sku(orderItemApi.getSku())
                .price(orderItemApi.getPrice())
                .quantity(orderItemApi.getQuantity())
                .order(order)
                .build();
    }

    public static OrderItemDto toOrderItemDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .sku(orderItem.getSku())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public static OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .orderNumber(order.getOrderNumber())
                .dateCreated(order.getDateCreated())
                .dateUpdated(order.getDateUpdated())
                .status(order.getStatus())
                .client(ClientMapper.toClientDto(order.getClient()))
                .trader(TraderMapper.toTraderDto(order.getTrader()))
                .orderItems(order.getOrderItems().stream().map(OrderMapper::toOrderItemDto).collect(Collectors.toList()))
                .build();
    }

}
