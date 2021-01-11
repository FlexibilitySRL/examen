package ar.com.plug.examen.app.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@JsonRootName(value = "seller")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SellerApi {

	private Long id;
	@NotNull
	private String name;
	
	@Override
	public String toString() {
		return "SellerApi [id=" + id + ", name=" + name + "]";
	}
	
}
