package ar.com.plug.examen.app.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("SellerAPI")
@JsonRootName(value = "seller")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerApi extends PersonApi {

	@ApiModelProperty(value = "Seller sells list")
	@JsonProperty
	@JsonManagedReference(value = "sellList")
	private List<PurchaseDetailApi> sellList;
	
	public SellerApi() {
		sellList = new ArrayList<PurchaseDetailApi>();
	}
	
	public List<PurchaseDetailApi> getSellList() {
		return sellList;
	}

	public void setSellList(List<PurchaseDetailApi> sellList) {
		this.sellList = sellList;
	}
	
	public void addSell(PurchaseDetailApi purchase) {
		this.getSellList().add(purchase);
	}

}