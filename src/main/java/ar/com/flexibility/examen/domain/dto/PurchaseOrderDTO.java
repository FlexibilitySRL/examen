package ar.com.flexibility.examen.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * A DTO for {@link ar.com.flexibility.examen.domain.service.impl.PurchaseOrderService} requests
 */
@Data
@ToString(callSuper = true)
public class PurchaseOrderDTO {

	private Long customerId;
	private Long sellerId;
	private List<PurchaseOrderItemDTO> items;

}
