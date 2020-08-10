package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "sale")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleApi {

    @JsonProperty (required = true) 
	private String code;
    @JsonProperty (required = true)
	private String clientIdentifier;
    @JsonProperty (required = true)
	private String productCode;
    @JsonProperty (required = true)
	private int productAmount;
    @JsonProperty (required = true)
	private String sellerIdentifier;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getClientIdentifier() {
		return clientIdentifier;
	}
	public void setClientIdentifier(String clientIdentifier) {
		this.clientIdentifier = clientIdentifier;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	public String getSellerIdentifier() {
		return sellerIdentifier;
	}
	public void setSellerIdentifier(String sellerIdentifier) {
		this.sellerIdentifier = sellerIdentifier;
	}
	
}
