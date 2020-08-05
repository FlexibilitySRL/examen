package ar.com.flexibility.examen.app.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.app.api.SellerApi;

public class SellerApiResponse extends SellerApi {

    @JsonProperty
	private List<SaleApiResponse> sales;

	public List<SaleApiResponse> getSales() {
		return sales;
	}
	public void setSales(List<SaleApiResponse> sales) {
		this.sales = sales;
	}
    
}
