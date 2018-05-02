package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "status")  //The @JsonRootName annotation is used – if wrapping is enabled – to specify the name of the root wrapper to be used.
@JsonIgnoreProperties(ignoreUnknown = false)
public class StatusApi {
	@JsonProperty
	private String code;
	
	@JsonProperty
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StatusApi(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public StatusApi() {
		super();
	}
	
	
}
