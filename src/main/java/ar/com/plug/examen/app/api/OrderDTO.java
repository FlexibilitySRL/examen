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
@NoArgsConstructor
public class OrderDTO
{
	private Long id;
	private ClientDTO clientDTO;
	private SellerDTO sellerDTO;
	private Date orderCreationDate;
	private OrderStatusEnum orderStatus;

}
