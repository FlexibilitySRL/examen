package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.OrderStatusEnum;
import ar.com.plug.examen.domain.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class OrderDTO
{
	private Long id;
	private ClientDTO client;
	private SellerDTO seller;
	private List<OrderItemsDTO> orderItems;
	private Date orderCreationDate;
	private OrderStatusEnum status;

	public OrderDTO() {}

	public OrderDTO(ClientDTO client, SellerDTO seller, List<OrderItemsDTO> orderItems, Date orderCreationDate,
			OrderStatusEnum status)
	{
		this.client = client;
		this.seller = seller;
		this.orderItems = orderItems;
		this.orderCreationDate = orderCreationDate;
		this.status = status;
	}
}
