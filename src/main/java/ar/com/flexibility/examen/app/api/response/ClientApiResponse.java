package ar.com.flexibility.examen.app.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.app.api.ClientApi;

public class ClientApiResponse extends ClientApi {

    @JsonProperty
	private List<SaleApiResponse> purchases;

	public List<SaleApiResponse> getPurchases() {
		return purchases;
	}
	public void setPurchases(List<SaleApiResponse> purchases) {
		this.purchases = purchases;
	}

}
