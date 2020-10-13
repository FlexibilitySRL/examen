package ar.com.plug.examen.app.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PurchaseAPI")
@JsonRootName(value = "Purchase")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseApi {
	
	@ApiModelProperty(value = "Purchase id", required = true)
	@JsonProperty
	private Long id;

	@ApiModelProperty(value = "Purchase description", required = true)
	@JsonProperty
	@NotNull
	private String description;
	
	@ApiModelProperty(value = "Purchase client id", required = true)
	@JsonProperty("client")
	@NotNull
	private Long clientId;
	
	@ApiModelProperty(value = "Purchase seller id", required = true)
	@JsonProperty("seller")
	@NotNull
	private Long sellerId;
	
	@ApiModelProperty(value = "Purchase items list. At least 1 item needed", required = true)
	@JsonProperty
	@NotEmpty
	@Valid
	private List<PurchaseItemApi> items;
	
	
	public PurchaseApi() {
		items = new ArrayList<PurchaseItemApi>();
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getClient() {
		return clientId;
	}

	public void setClient(Long clientId) {
		this.clientId = clientId;
	}

	public Long getSeller() {
		return sellerId;
	}

	public void setSeller(Long sellerId) {
		this.sellerId = sellerId;
	}

	public void setItems(List<PurchaseItemApi> items) {
		this.items = items;
	}

	public List<PurchaseItemApi> getItems() {
		return items;
	}
	
}
