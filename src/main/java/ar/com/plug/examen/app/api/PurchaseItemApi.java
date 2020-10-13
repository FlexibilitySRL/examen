package ar.com.plug.examen.app.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PurchaseItemAPI")
@JsonRootName(value = "PurchaseItem")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseItemApi {

	@ApiModelProperty(value = "Product quantity greater than 0", required = true)
	@JsonProperty
	@Min(value = 1)
	@Max(value = 999999)
	private Integer quantity;

	@ApiModelProperty(value = "Product id", required = true)
	@JsonProperty("product")
	@NotNull
	private Long productId;
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Long getProduct() {
		return productId;
	}

	public void setProduct(Long productId) {
		this.productId = productId;
	}
	

}
