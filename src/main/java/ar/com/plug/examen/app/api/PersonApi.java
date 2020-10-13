package ar.com.plug.examen.app.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PersonAPI")
@JsonRootName(value = "person")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonApi {
	
	@ApiModelProperty(value = "Id")
	@JsonProperty
	private Long id;

	@ApiModelProperty(value = "Name", required = true)
	@JsonProperty
	@NotNull
	private String name;

	@ApiModelProperty(value = "Surname", required = true)
	@JsonProperty
	@NotNull
	private String surname;

	@ApiModelProperty(value = "Email", required = true)
	@JsonProperty
	@Email
	private String email;
	
	public PersonApi() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}