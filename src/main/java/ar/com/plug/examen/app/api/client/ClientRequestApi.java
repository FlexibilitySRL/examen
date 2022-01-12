package ar.com.plug.examen.app.api.client;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "A client representation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequestApi {

	@NotBlank
	@Size(min = 4, max = 32)
	@JsonProperty(required = true)
	@ApiModelProperty(required = true, value = "An alphanumeric unique string identification for a client", allowableValues = "range(4, 32)")
	private String identification;

	@NotBlank
	@Size(min = 4, max = 32)
	@JsonProperty(required = true)
	@ApiModelProperty(required = true, value = "An alphabetic string for client names", allowableValues = "range(4, 32)")
	private String names;

	@NotBlank
	@Size(min = 4, max = 32)
	@JsonProperty(required = true)
	@ApiModelProperty(required = true, value = "An alphabetic string for client surname", allowableValues = "range(4, 32)")
	private String surnames;

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
