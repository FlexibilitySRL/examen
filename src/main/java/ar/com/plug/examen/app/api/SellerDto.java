package ar.com.plug.examen.app.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Schema(description = "Representation of seller's data.")
public class SellerDto
{
	@Schema(description = "Internal code of the seller", example = "sel-789")
	@NotBlank
	@Max(100)
	private String code;

	@Schema(description = "Legal document of the seller", example = "80011-99")
	@NotBlank
	@Max(100)
	private String document;

	@Schema(description = "Short description of the seller.", example = "AgrPro LLC")
	@NotBlank
	@Max(300)
	private String description;

	@Schema(description = "Indicates if a seller is active to be used to purchase products from him", example = "true")
	@NotNull
	private Boolean active;
}
