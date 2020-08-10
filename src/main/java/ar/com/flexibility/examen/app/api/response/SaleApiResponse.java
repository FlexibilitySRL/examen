package ar.com.flexibility.examen.app.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.app.api.SaleApi;

public class SaleApiResponse extends SaleApi {

    @JsonProperty
	private String date;
    @JsonProperty
	private String dateApproved;
    @JsonProperty
	private String status;
    @JsonProperty
	private double value;
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDateApproved() {
		return dateApproved;
	}
	public void setDateApproved(String dateApproved) {
		this.dateApproved = dateApproved;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
    
}
