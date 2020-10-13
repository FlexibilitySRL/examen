package ar.com.plug.examen.app.api;

import java.math.BigDecimal;
import java.math.MathContext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PurchaseDetailItemAPI")
@JsonRootName(value = "purchaseDetailItem")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseDetailItemApi {

	@ApiModelProperty(value = "Purchase item quantity", required = true)
	@JsonProperty
	private Integer quantity;

	@ApiModelProperty(value = "Purchase item product", required = true)
	@JsonProperty("product")
	private ProductApi product;
	
	@ApiModelProperty(value = "Purchase item total value", required = true)
	@JsonProperty
	private BigDecimal total;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public ProductApi getProduct() {
		return product;
	}

	public void setProduct(ProductApi productApi) {
		this.product = productApi;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getTotal() {
		return getProduct().getPrice().multiply(new BigDecimal(getQuantity()), MathContext.DECIMAL32);
	}

}
