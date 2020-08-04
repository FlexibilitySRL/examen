package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonApi {

    @JsonProperty
	private String identifier;
    @JsonProperty
	private String name;
    @JsonProperty
	private String surname;
    
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
