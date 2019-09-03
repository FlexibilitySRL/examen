package ar.com.flexibility.examen.domain.dto;

import java.math.BigDecimal;

import ar.com.flexibility.examen.domain.base.BaseDTO;

public class ProductDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private String category;
	private Long code;
	private BigDecimal price;

	public ProductDTO() {
	}

	public ProductDTO(String name, String description, String category, Long code, BigDecimal price) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.code = code;
		this.price = price;
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
