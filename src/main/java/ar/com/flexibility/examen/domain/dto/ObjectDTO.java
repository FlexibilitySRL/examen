package ar.com.flexibility.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ObjectDTO<T> {
	@JsonProperty
	private long id;
	
	@JsonProperty
	private T data;
	
	public ObjectDTO(long id, T data) {
		this.id = id;
		this.data = data;
	}
	
	protected ObjectDTO() {
		
	}
}
