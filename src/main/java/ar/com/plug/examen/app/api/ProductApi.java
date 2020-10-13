package ar.com.plug.examen.app.api;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ProductAPI")
@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductApi {
	
	@ApiModelProperty(value = "Product id")
	@JsonProperty
	private Long id;
	
	@ApiModelProperty(value = "Product name", required = true)
	@JsonProperty
	@NotNull
	private String name;
	
	@ApiModelProperty(value = "Product price (########.##)", required = true)
	@JsonProperty
	@DecimalMin(value = "0.1", inclusive = false)
	@Digits(integer=8, fraction=2)
	@NotNull
	private BigDecimal price;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}