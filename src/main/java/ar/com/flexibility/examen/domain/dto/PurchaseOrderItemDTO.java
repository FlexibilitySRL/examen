package ar.com.flexibility.examen.domain.dto;

import lombok.Data;
import lombok.ToString;

/**
 * A DTO for {@link ar.com.flexibility.examen.domain.service.impl.PurchaseOrderService} requests
 */
@Data
@ToString(callSuper = true)
public class PurchaseOrderItemDTO {

	private Long productId;
	private Integer quantity;
}
