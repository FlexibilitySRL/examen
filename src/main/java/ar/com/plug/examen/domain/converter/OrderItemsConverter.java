package ar.com.plug.examen.domain.converter;

import ar.com.plug.examen.app.api.OrderItemsDTO;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderItemsConverter
{
	@Autowired
	private OrderConverter orderConverter;

	@Autowired
	private ProductConverter productConverter;

	public OrderItemsDTO orderItemToOrderItemDTO(OrderItems orderItems) {

		return OrderItemsDTO.builder()
				.product(productConverter.toDTO(orderItems.getProduct()))
				.quantity(orderItems.getQuantity())
				.order(orderItems.getOrders())
				.build();
	}

	public OrderItems orderItemDTOToOrderItem(OrderItemsDTO orderItemsDTO) {

		return OrderItems.builder()
				.product(productConverter.toModel(orderItemsDTO.getProduct()))
				.quantity(orderItemsDTO.getQuantity())
				.orders(orderItemsDTO.getOrder())
				.build();
	}

	public List<OrderItemsDTO> orderItemsToOrderItemsDTOList(List<OrderItems> orderItems) {
		return orderItems.stream()
				.map(this::orderItemToOrderItemDTO)
				.collect(Collectors.toList());
	}

	public List<OrderItems> orderItemsDTOToOrderItemsList(List<OrderItemsDTO> orderItemsDTO) {
		return orderItemsDTO.stream()
				.map(this::orderItemDTOToOrderItem)
				.collect(Collectors.toList());
	}
}
