package ar.com.plug.examen.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PRODUCT")
public class Product extends EntityModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8441177669410283005L;

	@NotBlank
	@Size(min = 4, max = 32)
	private String name;

	@NotBlank
	@Size(min = 4, max = 128)
	private String description;

	@NotNull
	@Positive
	private BigDecimal price;

	@NotNull
	@PositiveOrZero
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
