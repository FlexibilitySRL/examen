package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductApi {

	@JsonProperty
	private Long id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
