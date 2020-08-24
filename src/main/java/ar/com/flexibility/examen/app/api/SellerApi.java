package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SellerApi {

	@JsonProperty
	private Long id;
	@JsonProperty
	private String firstName;
	@JsonProperty
	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
