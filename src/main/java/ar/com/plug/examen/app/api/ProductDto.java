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
@Schema(description = "Representation of product data.")
public class ProductDto
{
	@Schema(description = "Sku which identifies a product in the stock", example = "456abc")
	@NotBlank
	private String sku;

	@Schema(description = "Sku of the vendor", example = "vend-32431")
	@NotBlank
	private String skuVendor;

	@Schema(description = "Cost value of the product at the time it was bought", example = "10")
	@NotNull
	private BigDecimal cost;

	@Schema(description = "Price used to sell the product", example = "20")
	@NotNull
	private BigDecimal salePrice;

	@Schema(description = "Description of the product", example = "Wheel")
	@NotBlank
	private String description;

	@Schema(description = "Identifies if the product is active to be sell", example = "true")
	@NotNull
	private Boolean active;

	@Schema(description = "Id of the seller from who we bought the product", example = "2")
	@NotNull
	private Long sellerId;

	@Schema(description = "Amount of products in the stock", example = "45")
	@NotNull
	private Integer stockQuantity;
}
