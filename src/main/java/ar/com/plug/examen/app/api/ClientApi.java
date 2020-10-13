package ar.com.plug.examen.app.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ClientAPI")
@JsonRootName(value = "client")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientApi extends PersonApi {
	
	@ApiModelProperty(value = "Client purchase list")
	@JsonProperty
	@JsonManagedReference(value = "purchaseList")
	private List<PurchaseDetailApi> purchaseList;
	
	public ClientApi() {
		purchaseList = new ArrayList<PurchaseDetailApi>();
	}

	public void setPurchaseList(List<PurchaseDetailApi> purchaseList) {
		this.purchaseList = purchaseList;
	}
	
	public List<PurchaseDetailApi> getPurchaseList() {
		return purchaseList;
	}
	
	public void addPurchase(PurchaseDetailApi purchase) {
		this.getPurchaseList().add(purchase);
	}

}