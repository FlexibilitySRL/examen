package ar.com.flexibility.examen.app.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {

	@NotNull
	private Long productId;

	@Min(1)
	private Integer quantity;

}
