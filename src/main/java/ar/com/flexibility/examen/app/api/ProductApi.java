/**
 * 
 */
package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rosalizaracho
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductApi {
	
	@JsonProperty
	private String name;
	@JsonProperty
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	

}
