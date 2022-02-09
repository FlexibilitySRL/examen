package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.OrderStatusEnum;
import ar.com.plug.examen.domain.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class OrderItemsDTO
{
	private ProductDTO product;
	private Long quantity;
	@JsonIgnore
	private Order order;
}
