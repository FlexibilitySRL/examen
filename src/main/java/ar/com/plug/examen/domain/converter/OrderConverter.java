package ar.com.plug.examen.domain.converter;

import ar.com.plug.examen.app.api.OrderDTO;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter
{
	@Autowired
	private ClientConverter clientConverter;

	@Autowired
	private SellerConverter sellerConverter;

	@Autowired
	private OrderItemsConverter orderItemsConverter;

	public OrderDTO orderToOrderDTO(Order order) {

		return OrderDTO.builder()
				.id(order.getId())
				.client(clientConverter.toDTO(order.getClient()))
				.seller(sellerConverter.toDTO(order.getSeller()))
				.orderCreationDate(order.getCreationDate())
				.status(order.getStatus())
				.orderItems(orderItemsConverter.orderItemsToOrderItemsDTOList(order.getOrderItems()))
				.build();
	}

	public Order orderDTOToOrder(OrderDTO orderDTO) {

		return Order.builder()
				.client(clientConverter.toModel(orderDTO.getClient()))
				.seller(sellerConverter.toModel(orderDTO.getSeller()))
				.creationDate(orderDTO.getOrderCreationDate())
				.status(orderDTO.getStatus())
				.orderItems(orderItemsConverter.orderItemsDTOToOrderItemsList(orderDTO.getOrderItems()))
				.build();
	}

	public List<OrderDTO> orderListToOrderDTOList(List<Order> orders) {
		return orders.stream()
				.map(this::orderToOrderDTO)
				.collect(Collectors.toList());
	}
}
