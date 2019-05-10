package ar.com.flexibility.examen.app.api;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ar.com.flexibility.examen.domain.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Product")
public class ProductApi
{
	@JsonProperty
	@ApiModelProperty(value = "ID", position = 0)
	private Long id;

	@JsonProperty
	@ApiModelProperty(value = "Description", required = true, position = 1)
	private String description;

	@JsonProperty
	@ApiModelProperty(value = "Price", required = true, position = 2)
	private BigDecimal price;

	public ProductApi()
	{
	}

	public ProductApi(Product product)
	{
		this.id = product.getId();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(description, id, price);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProductApi))
			return false;
		ProductApi other = (ProductApi) obj;
		return Objects.equals(description, other.getDescription()) && Objects.equals(id, other.getId())
				&& Objects.equals(price, other.getPrice());
	}
}
