package ar.com.plug.examen.app.api;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
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
@Schema(description = "Representation of purchase's data.")
public class PurchaseDto
{
	@Schema(description = "Number of receipt which is given to the client.", example = "9079868")
	@NotBlank
	private String receiptNumber;

	@Schema(description = "Status which indicates if the purchase was approved", example = "true")
	@NotNull
	private Boolean approve;

	@Schema(description = "Id of the client who made the purchase", example = "1")
	@NotNull
	private Long clientId;
}
