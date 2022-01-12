package ar.com.plug.examen.app.api.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "A client representation")
public class ClientResponseApi {

	@JsonProperty
	@ApiModelProperty(required = true, value = "An autogenerate unique id for client")
	private Long id;

	@JsonProperty
	@ApiModelProperty(required = true, value = "An alphanumeric unique string identification for a client", allowableValues = "range(4, 32)")
	private String identification;

	@JsonProperty
	@ApiModelProperty(required = true, value = "An alphabetic string for client names", allowableValues = "range(4, 32)")
	private String names;

	@JsonProperty
	@ApiModelProperty(required = true, value = "An alphabetic string for client surname", allowableValues = "range(4, 32)")
	private String surnames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

}
