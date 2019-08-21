package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class InvalidUpdateErrorResponse extends ErrorResponse {
	@JsonProperty
	private long objectId;
	
	public InvalidUpdateErrorResponse(long objectId) {
		this.objectId = objectId;
	}

	public long getObjectId() {
		return objectId;
	}
	
	
}
