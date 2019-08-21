package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ClientIsInAPurchaseOrderErrorResponse extends ErrorResponse {
	@JsonProperty
	private long clientId;
	
	public ClientIsInAPurchaseOrderErrorResponse(long clientId) {
		this.clientId = clientId;
	}
	
	protected ClientIsInAPurchaseOrderErrorResponse() {
		
	}
	
	public long getClientId() {
		return this.clientId;
	}
}
