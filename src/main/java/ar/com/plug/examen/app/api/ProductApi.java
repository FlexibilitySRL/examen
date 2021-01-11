package ar.com.plug.examen.app.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductApi {

	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private Double price;
	
	@Override
	public String toString() {
		return "ProductApi [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}
	
	
}
