package ar.com.plug.examen.app.api;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Representation of purchase's data.")
public class PurchaseDto
{
	@Schema(description = "Number of receipt which is given to the client.", example = "9079868")
	private String receiptNumber;

	@Schema(description = "Total amount of the purchase", example = "100.00")
	private BigDecimal total;

	@Schema(description = "Amount of taxes corresponding to the purchase", example = "10.00")
	private BigDecimal taxes;

	@Schema(description = "Status which indicates if the purchase was approved", example = "true")
	private Boolean approve;

	@Schema(description = "Id of the client who made the purchase", example = "1")
	private Long clientId;
}
