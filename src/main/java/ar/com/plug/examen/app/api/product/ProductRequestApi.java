package ar.com.plug.examen.app.api.product;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestApi {

	@NotBlank
	@Size(min = 4, max = 32)
	@JsonProperty(required = true)
	private String name;

	@NotBlank
	@Size(min = 4, max = 128)
	@JsonProperty(required = true)
	private String description;

	@NotNull
	@Positive
	@JsonProperty(required = true)
	private BigDecimal price;

	@NotNull
	@PositiveOrZero
	@JsonProperty(required = true)
	private Long stock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

}
