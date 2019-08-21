package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ClientDoesNotExistErrorResponse extends ErrorResponse {
	@JsonProperty
	private long clientId;
	
	public ClientDoesNotExistErrorResponse(long clientId) {
		this.clientId = clientId;
	}
	
	protected ClientDoesNotExistErrorResponse() {
		
	}
	
	public long getClientId() {
		return this.clientId;
	}
}
