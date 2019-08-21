package ar.com.flexibility.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.LegalClient;
import ar.com.flexibility.examen.domain.service.exceptions.UnexpectedNullValueException;

public final class LegalClientDTO extends ClientDTO {
	@JsonProperty
	private String name;

	/**
	 * @post Crea un DTO para el cliente jurídico especificado
	 */
	public LegalClientDTO(LegalClient legalClient) {
		super(legalClient);
		
		this.name = legalClient.getName();
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public <R> R accept(ClientDTOVisitor<R> visitor) {
		return visitor.visit(this);
	}

	@Override
	public LegalClient toEntity() {
		LegalClient client = new LegalClient();
		
		this.updateEntity(client);
		
		return client;
	}

	/**
	 * @post Actualiza la entidad
	 */
	public void updateEntity(LegalClient client) {
		if ( client == null )
			throw new NullPointerException();
		
		if ( this.getName() == null )
			throw new UnexpectedNullValueException();
		
		if ( this.getCommercialAddress() == null )
			throw new UnexpectedNullValueException();
		
		client.setCuit(this.getCuit());
		client.setName(this.getName());
		client.setCommercialAddress(this.getCommercialAddress().toValueObject());
	}
}
