package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.OrderStatusEnum;
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
public class OrderRequest
{
	private Long clientId;
	private Long sellerId;
	private Date orderCreationDate;
	private List<ProductRequest> productRequests;
}
