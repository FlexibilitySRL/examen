package ar.com.plug.examen.domain.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


/**
 * @author yeissonboada
 * @date 2022-01-25
 */
@JsonPropertyOrder({"error", "details", "status"})
public class ErrorResponse
{

	@JsonProperty("status-code")
	private String statusCode;
	
	@JsonProperty
	private String message;
	
	@JsonProperty
	private List<String> details;

	public ErrorResponse(String statusCode, String message, List<String> details) {
		this.statusCode = statusCode;
		this.message = message;
		this.details = details;
	}
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
