package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchasesApi {

	@JsonProperty
	private Long id;
	@JsonProperty
	private String description;
	@JsonProperty
	private Long value;
	@JsonProperty
	private ClientApi clientApi;
	@JsonProperty
	private ProductApi productApi;
	@JsonProperty
	private SellerApi sellerApi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public ClientApi getClientApi() {
		return clientApi;
	}

	public void setClientApi(ClientApi clientApi) {
		this.clientApi = clientApi;
	}

	public ProductApi getProductApi() {
		return productApi;
	}

	public void setProductApi(ProductApi productApi) {
		this.productApi = productApi;
	}

	public SellerApi getSellerApi() {
		return sellerApi;
	}

	public void setSellerApi(SellerApi sellerApi) {
		this.sellerApi = sellerApi;
	}
}
