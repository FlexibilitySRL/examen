package ar.com.plug.examen.app.api;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Data representation of the details of a purchase.")
public class PurchaseDetailDto
{
	@Schema(description = "Internal code of a product", example = "789")
	@NotNull
	private Long productId;

	@Schema(description = "Amount of items of the defined product", example = "10")
	@NotNull
	private Integer quantity;

	@Schema(description = "Internal code of the purchase", example = "888")
	@NotNull
	private Long purchaseId;

	@Schema(description = "Unit price of the item.", example = "10.00")
	@NotNull
	private BigDecimal unitSalePrice;
}
