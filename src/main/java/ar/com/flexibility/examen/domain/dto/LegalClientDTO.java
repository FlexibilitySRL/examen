package ar.com.flexibility.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.LegalClient;

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
		
		client.setCuit(client.getCuit());
		client.setCommercialAddress( this.getCommercialAddress().toValueObject() );
		client.setName(client.getName());
		
		return client;
	}

}
