package ar.com.plug.examen.app.api.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionItemRequestApi {

	@JsonProperty(required = true)
	private RequestId product;

	@JsonProperty(required = true)
	private Long quantity;

	public RequestId getProduct() {
		return product;
	}

	public void setProduct(RequestId product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
